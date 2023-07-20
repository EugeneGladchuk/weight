package com.example.weightofring.ui.fragments.cross_fragment

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.weightofring.ui.fragments.cross_fragment.model.TypeCross
import com.example.weightofring.ui.fragments.ring_fragment.model.DensityGoldEnum

class CalculateCrossViewModel(application: Application) : AndroidViewModel(application) {

    data class CrossEditTextState(
        val text: String,
        val error: Boolean
    )

    private val _length = MutableLiveData(CrossEditTextState(text = "", error = false))
    val length: LiveData<CrossEditTextState> get() = _length

    private val _width = MutableLiveData(CrossEditTextState(text = "", error = false))
    val width: LiveData<CrossEditTextState> get() = _width

    private val _thickness = MutableLiveData(CrossEditTextState(text = "", error = false))
    val thickness: LiveData<CrossEditTextState> get() = _thickness

    private val _rayWidth = MutableLiveData(CrossEditTextState(text = "", error = false))
    val rayWidth: LiveData<CrossEditTextState> get() = _rayWidth

    private val _typeCross = MutableLiveData(TypeCross.SQUARE)
    val typeCross: LiveData<TypeCross> get() = _typeCross

    private val _typeMetal = MutableLiveData(DensityGoldEnum.GOLD_750)
    val typeMetal: LiveData<DensityGoldEnum> get() = _typeMetal

    private val _result = MutableLiveData<Double>()
    val result: LiveData<Double> get() = _result

    private fun checkAllValues() {

        var lengthDouble = 0.0
        try {
            val length = _length.value ?: throw Throwable("INVALID_SIZE")
            lengthDouble = length.text.toDouble()
        } catch (ex: Exception) {
            _length.value = _length.value?.copy(error = true)
        }

        var widthDouble = 0.0
        try {
            val width = _width.value ?: throw Throwable("INVALID_SIZE")
            widthDouble = width.text.toDouble()
        } catch (ex: Exception) {
            _width.value = _width.value?.copy(error = true)
        }

        var thicknessDouble = 0.0
        try {
            val thickness = _thickness.value ?: throw Throwable("INVALID_SIZE")
            thicknessDouble = thickness.text.toDouble()
        } catch (ex: Exception) {
            _thickness.value = _thickness.value?.copy(error = true)
        }

        var rayWidthDouble = 0.0
        try {
            val rayWidth = _rayWidth.value ?: throw Throwable("INVALID_SIZE")
            rayWidthDouble = rayWidth.text.toDouble()
        } catch (ex: Exception) {
            _rayWidth.value = _rayWidth.value?.copy(error = true)
        }


    }

    fun lengthTextChanged(length: String) {
        updateLength(length)
    }

    private fun updateLength(length: String) {
        if (_length.value?.text != length) {
            _length.value = _length.value?.copy(text = length, error = false)
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

    fun thicknessTextChanged(thickness: String) {
        updateThickness(thickness)
    }

    private fun updateThickness(thickness: String) {
        if (_thickness.value?.text != thickness) {
            _thickness.value = _thickness.value?.copy(text = thickness, error = false)
        }
    }

    fun rayWidthTextChanged(rayWidth: String) {
        updateRayWidth(rayWidth)
    }

    private fun updateRayWidth(rayWidth: String) {
        if (_rayWidth.value?.text != rayWidth) {
            _rayWidth.value = _rayWidth.value?.copy(text = rayWidth, error = false)
        }
    }

    fun updateTypeCross(typeCross: TypeCross) {
        if (_typeCross.value != typeCross) {
            _typeCross.value = typeCross
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