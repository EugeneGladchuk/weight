package com.example.weightofring.ui.viewModels

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.weightofring.domain.model.DensityGoldEnum
import com.example.weightofring.data.database.AppDatabase
import com.example.weightofring.data.database.ringresult.RingResult
import com.example.weightofring.domain.model.TypeRing
import kotlinx.coroutines.launch
import kotlin.math.PI
import kotlin.math.floor

class CalculateRingViewModel(application: Application) : AndroidViewModel(application) {

    private val db = AppDatabase.getDatabase(application)

    data class RingEditTextState(
        val text: String,
        val error: Boolean
    )

    val myDataList: LiveData<List<RingResult>> = db.ringResultDao().getAll()

    private val _width = MutableLiveData(RingEditTextState(text = "", error = false))
    val width: LiveData<RingEditTextState> get() = _width

    private val _size = MutableLiveData(RingEditTextState(text = "", error = false))
    val size: LiveData<RingEditTextState> get() = _size

    private val _thickness = MutableLiveData(RingEditTextState(text = "", error = false))
    val thickness: LiveData<RingEditTextState> get() = _thickness

    private val _typeMetal = MutableLiveData(DensityGoldEnum.GOLD_750)
    val typeMetal: LiveData<DensityGoldEnum> get() = _typeMetal

    private val _typeRing = MutableLiveData(TypeRing.CLASSIC)
    val typeRing: LiveData<TypeRing> get() = _typeRing

    private val _lengthRingBase = MutableLiveData<Double>()
    val lengthRingBase: LiveData<Double> get() = _lengthRingBase

    private val _result = MutableLiveData<Double>()
    val result: LiveData<Double> get() = _result


    private fun calculateWeightOfRing() {

        var widthDouble = 0.0
        try {
            val width = _width.value ?: throw Throwable("INVALID_SIZE")
            widthDouble = width.text.toDouble()
        } catch (ex: Exception) {
            _width.value = _width.value?.copy(error = true)
        }

        var sizeDouble = 0.0
        try {
            val size = _size.value ?: throw Throwable("INVALID_SIZE")
            sizeDouble = size.text.toDouble()
        } catch (ex: Exception) {
            _size.value = _size.value?.copy(error = true)
        }

        var thicknessDouble = 0.0
        try {
            val thickness = _thickness.value ?: throw Throwable("INVALID_SIZE")
            thicknessDouble = thickness.text.toDouble()
        } catch (ex: Exception) {
            _thickness.value = _thickness.value?.copy(error = true)
        }

        val typeRing = _typeRing.value.toString()

        val ringCutArea = calculateCutArea(widthDouble, thicknessDouble)

        val middleDiameter = sizeDouble + thicknessDouble

        val lengthBase = middleDiameter * PI

        val ringVolume = lengthBase * ringCutArea

        val typeMetal = _typeMetal.value ?: throw Throwable("INVALID_SIZE")

        val resultFloor = (ringVolume * typeMetal.dens)

        val typeMetalForResultList = typeMetal.typeName

        val resultWeightRing = floor(resultFloor * 100.0).div(100.0)

        val resultLengthBase = floor(lengthBase * 100.0).div(100.0)

        if ( _width.value?.error == true || _size.value?.error == true || _thickness.value?.error == true ) {
            _result.value = 0.0
            _lengthRingBase.value = 0.0
        } else {
            _result.value = resultWeightRing
            _lengthRingBase.value = resultLengthBase
            saveToDatabase(typeRing, widthDouble, sizeDouble, thicknessDouble, typeMetalForResultList, resultWeightRing, resultLengthBase )
        }
    }

    private fun saveToDatabase(typeRing: String,
                               widthDouble: Double,
                               sizeDouble: Double,
                               thicknessDouble: Double,
                               typeMetal: String,
                               result: Double,
                               resultLengthBase: Double) {
        viewModelScope.launch {
            val ringResult = RingResult(
                null,
                typeRing,
                widthDouble.toString(),
                sizeDouble.toString(),
                thicknessDouble.toString(),
                typeMetal,
                result.toString(),
                resultLengthBase.toString())
            db.ringResultDao().insertRingResult(ringResult)
        }
    }

    private fun calculateCutArea(width: Double, thickness: Double): Double {
        val europeanCutArea = width * thickness
        val classicCutArea = ((width/2) * thickness * 3.14)/2
        return if (_typeRing.value == TypeRing.CLASSIC) {
            classicCutArea
        } else {
            europeanCutArea
        }
    }

    fun deleteButtonClick(item: RingResult) {
        viewModelScope.launch {
            db.ringResultDao().deleteRingResult(item)
        }
    }

    fun widthTextChanged(width: String) {
        updateWidth(width)
    }

    private fun updateWidth(width: String) {
        if (_width.value?.text != width) {
            _width.value = _width.value?.copy(text = width, error = false)
        }
    }

    fun sizeTextChanged(size: String) {
        updateSize(size)
    }

    private fun updateSize(size: String) {
        if (_size.value?.text != size) {
            _size.value = _size.value?.copy(text = size, error = false)
        }
    }

    fun thicknessTextChanged(thickness: String) {
        updateThickness(thickness)
    }

    private fun updateThickness(thickness: String) {
        if (_thickness.value?.text != thickness) {
            _thickness.value = _thickness.value?.copy(text = thickness, error = false)
        }
    }

    fun updateTypeRing(typeRing: TypeRing) {
        if (_typeRing.value != typeRing) {
            _typeRing.value = typeRing
        }
    }

    fun updateTypeMetal(typeMetal: DensityGoldEnum) {
        if (_typeMetal.value != typeMetal) {
            _typeMetal.value = typeMetal
        }
    }

    fun calculate() {
        calculateWeightOfRing()
    }

}