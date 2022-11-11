package com.example.weightofring

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import kotlin.math.floor

class CalculateRingViewModel : ViewModel() {

    data class EditTextState(
        val text: String,
        val error: Boolean
    )

    private val _width = MutableLiveData<EditTextState>(EditTextState(text = "", error = false))
    val width: LiveData<EditTextState> get() = _width

    private val _size = MutableLiveData<EditTextState>(EditTextState(text = "", error = false))
    val size: LiveData<EditTextState> get() = _size

    private val _thickness = MutableLiveData<EditTextState>(EditTextState(text = "", error = false))
    val thickness: LiveData<EditTextState> get() = _thickness

    private val _typeMetal = MutableLiveData<DensityGoldEnum>(DensityGoldEnum.GOLD_585)
    val typeMetal: LiveData<DensityGoldEnum> get() = _typeMetal

    private val _result = MutableLiveData<String>()
    val result: LiveData<String> get() = _result

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

        if ( _width.value?.error == true || _size.value?.error == true || _thickness.value?.error == true ) {
            return
        }

        val areaSize = ((sizeDouble * sizeDouble) / 4) * 3.14
        val insideDiameter = (thicknessDouble * 2.0) + sizeDouble
        val areaTotal = ((insideDiameter * insideDiameter) / 4) * 3.14
        val areaRingSide = areaTotal - areaSize

        val volumeRing = areaRingSide * widthDouble

        val typeMetal = _typeMetal.value ?: throw Throwable("INVALID_SIZE")

        val resultFloor = (volumeRing * typeMetal.dens)

        _result.value = floor(resultFloor * 100.0).div(100.0).toString()
    }

    fun updateWidth(width: String) {
        if (_width.value?.text != width) {
            _width.value = _width.value?.copy(text = width, error = false)
        }
    }

    fun updateSize(size: String) {
        if (_size.value?.text != size) {
            _size.value = _size.value?.copy(text = size, error = false)
        }
    }

    fun updateThickness(thickness: String) {
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