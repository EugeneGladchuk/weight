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

    private val _size = MutableLiveData<EditTextState>(EditTextState(text = "", error = false))
    val size: LiveData<EditTextState> get() = _size

    private val _width = MutableLiveData<String>()
    val width: LiveData<String> get() = _width

    private val _thickness = MutableLiveData<String>()
    val thickness: LiveData<String> get() = _thickness

    private val _typeMetal = MutableLiveData<DensityGoldEnum>(DensityGoldEnum.GOLD_585)
    val typeMetal: LiveData<DensityGoldEnum> get() = _typeMetal

    private val _result = MutableLiveData<String>()
    val result: LiveData<String> get() = _result

    private fun calculateWeightOfRing() {

        var sizeDouble = 0.0
        try {
            val size = _size.value ?: throw Throwable("INVALID_SIZE")
            sizeDouble = size.text.toDouble()
        } catch (ex: Exception) {
            _size.value = _size.value?.copy(error = true)
        }

        val width = _width.value ?: throw Throwable("INVALID_SIZE")
        val widthDouble = width.toDouble()
        val thickness = _thickness.value ?: throw Throwable("INVALID_SIZE")
        val thicknessDouble = thickness.toDouble()

        if (_size.value?.error == true /* TODO || width.. || thicness */) {
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

    fun updateSize(size: String) {
        if (_size.value?.text != size) {
            _size.value = _size.value?.copy(text = size)
        }
    }

    fun updateWidth(width: String) {
        if (_width.value != width) {
            _width.value = width
        }
    }

    fun updateThickness(thickness: String) {
        if (_thickness.value != thickness) {
            _thickness.value = thickness
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