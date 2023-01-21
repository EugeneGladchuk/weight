package com.example.weightofring

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.weightofring.database.AppDatabase
import com.example.weightofring.database.ringresult.RingResult
import kotlinx.coroutines.launch
import kotlin.math.floor

class CalculateRingViewModel(application: Application) : AndroidViewModel(application) {

    private val db = AppDatabase.getDatabase(application)

    data class RingEditTextState(
        val text: String,
        val error: Boolean
    )


    private val _width = MutableLiveData(RingEditTextState(text = "", error = false))
    val width: LiveData<RingEditTextState> get() = _width

    private val _size = MutableLiveData(RingEditTextState(text = "", error = false))
    val size: LiveData<RingEditTextState> get() = _size

    private val _thickness = MutableLiveData(RingEditTextState(text = "", error = false))
    val thickness: LiveData<RingEditTextState> get() = _thickness

    private val _typeMetal = MutableLiveData(DensityGoldEnum.GOLD_585)
    val typeMetal: LiveData<DensityGoldEnum> get() = _typeMetal

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

        val areaSize = ((sizeDouble * sizeDouble) / 4) * 3.14
        val insideDiameter = (thicknessDouble * 2.0) + sizeDouble
        val areaTotal = ((insideDiameter * insideDiameter) / 4) * 3.14
        val areaRingSide = areaTotal - areaSize

        val volumeRing = areaRingSide * widthDouble

        val typeMetal = _typeMetal.value ?: throw Throwable("INVALID_SIZE")

        val resultFloor = (volumeRing * typeMetal.dens)

        val typeMetalForResultList = typeMetal.typeName

        val resWeight = floor(resultFloor * 100.0).div(100.0)

        if ( _width.value?.error == true || _size.value?.error == true || _thickness.value?.error == true ) {
            _result.value = 0.0
        } else {
            _result.value = resWeight
            saveToDatabase(widthDouble, sizeDouble, thicknessDouble, typeMetalForResultList, resWeight)
        }
    }

    private fun saveToDatabase(widthDouble: Double, sizeDouble: Double, thicknessDouble: Double, typeMetal: String, result: Double) {
        viewModelScope.launch {
            val ringResult = RingResult(
                null,
                widthDouble.toString(),
                sizeDouble.toString(),
                thicknessDouble.toString(),
                typeMetal,
                result.toString())
            db.ringResultDao().insertRingResult(ringResult)
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

    fun updateTypeMetal(typeMetal: DensityGoldEnum) {
        if (_typeMetal.value != typeMetal) {
            _typeMetal.value = typeMetal
        }
    }

    fun calculate() {
        calculateWeightOfRing()
    }

}