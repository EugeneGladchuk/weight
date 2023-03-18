package com.example.weightofring.ui.fragments.ring_fragment

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.weightofring.domain.model.DensityGoldEnum
import com.example.weightofring.data.database.AppDatabase
import com.example.weightofring.data.database.ringresult.RingResult
import com.example.weightofring.domain.model.TypeRing
import com.example.weightofring.domain.use_case.CalculateRingWeightUseCase
import kotlinx.coroutines.launch
import kotlin.math.floor

class CalculateRingViewModel(application: Application) : AndroidViewModel(application) {

    private val db = AppDatabase.getDatabase(application)

    private val calculateRingWeightUseCase = CalculateRingWeightUseCase()

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


    private fun checkAllValues() {

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
        val typeMetal = _typeMetal.value ?: throw Throwable("INVALID_SIZE")
        val lengthBase = floor(calculateRingWeightUseCase.calculateLengthBase(sizeDouble, thicknessDouble) * 100.0).div(100.0)
        val result = calculateRingWeightUseCase.calculateRing(typeRing, widthDouble, sizeDouble, thicknessDouble, typeMetal)

        if ( _width.value?.error == true || _size.value?.error == true || _thickness.value?.error == true ) {
            _result.value = 0.0
            _lengthRingBase.value = 0.0
        } else {
            _result.value = result
            _lengthRingBase.value = lengthBase
            saveToDatabase(typeRing, widthDouble, sizeDouble, thicknessDouble, typeMetal.toString(), result, lengthBase)
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

    fun onResultButtonClicked() {
        checkAllValues()
    }
}