package com.example.weightofring.ui.fragments.calculate_wire_fragment

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.weightofring.domain.model.Lists
import com.example.weightofring.domain.use_case.CalculationsWireUseCase
import com.example.weightofring.ui.fragments.calculate_wire_fragment.model.TypeCalculateForWire
import com.example.weightofring.ui.fragments.ring_fragment.model.DensityGoldEnum

class CalculateWireViewModel(application: Application) : AndroidViewModel(application) {

    private val calculationsWireUseCase = CalculationsWireUseCase()

    data class WireEditTextState(
        val text: String,
        val error: Boolean
    )

    private val _listForSpinnerWire = MutableLiveData(Lists.listForSpinnerWire)
    val listForSpinnerWire: LiveData<MutableList<TypeCalculateForWire>> get() = _listForSpinnerWire

    private val _wireSpinnerPosition = MutableLiveData(0)
    val wireSpinnerPosition: LiveData<Int> get() = _wireSpinnerPosition

    private val _typeMetal = MutableLiveData(DensityGoldEnum.GOLD_750)
    val typeMetal: LiveData<DensityGoldEnum> get() = _typeMetal

    private val _firstParameter = MutableLiveData(WireEditTextState(text = "", error = false))
    val firstParameter: LiveData<WireEditTextState> get() = _firstParameter

    private val _secondParameter = MutableLiveData(WireEditTextState(text = "", error = false))
    val secondParameter: LiveData<WireEditTextState> get() = _secondParameter

    private val _result = MutableLiveData<Double>()
    val result: LiveData<Double> get() = _result

    private fun checkAllValues() {

        var firstParameterDouble = 0.0
        try {
            val firstParameter = _firstParameter.value ?: throw Throwable("INVALID_SIZE")
            firstParameterDouble = firstParameter.text.toDouble()
        } catch (ex: Exception) {
            _firstParameter.value = _firstParameter.value?.copy(error = true)
        }

        var secondParameterDouble = 0.0
        try {
            val secondParameter = _secondParameter.value ?: throw Throwable("INVALID_SIZE")
            secondParameterDouble = secondParameter.text.toDouble()
        } catch (ex: Exception) {
            _secondParameter.value = _secondParameter.value?.copy(error = true)
        }

        val typeMetal = _typeMetal.value ?: throw Throwable("INVALID_SIZE")
        val result = when (_wireSpinnerPosition.value) {
            0 -> calculationsWireUseCase.calculateLengthWire(firstParameterDouble, secondParameterDouble, typeMetal)
            1 -> calculationsWireUseCase.calculateDiameterWire(firstParameterDouble, secondParameterDouble, typeMetal)
            else -> calculationsWireUseCase.calculateWeightBaseForWire(firstParameterDouble, secondParameterDouble, typeMetal)
        }

        if ( _firstParameter.value?.error == true || _secondParameter.value?.error == true) {
            _result.value = 0.0
        } else {
            _result.value = result
        }

    }

    fun wireSpinnerChanged(wireSpinnerPosition: Int) {
        updateWireSpinner(wireSpinnerPosition)
    }

    private fun updateWireSpinner(wireSpinnerPosition: Int) {
        if (_wireSpinnerPosition.value != wireSpinnerPosition) {
            _wireSpinnerPosition.value = wireSpinnerPosition
        }
    }

    fun firstParameterChanged(param: String) {
        updateFirstParameter(param)
    }

    private fun updateFirstParameter(param: String) {
        if (_firstParameter.value?.text != param) {
            _firstParameter.value = _firstParameter.value?.copy(text = param, error = false)
        }
    }

    fun secondParameterChanged(param: String) {
        updateSecondParameter(param)
    }

    private fun updateSecondParameter(param: String) {
        if (_secondParameter.value?.text != param) {
            _secondParameter.value = _secondParameter.value?.copy(text = param, error = false)
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

    fun resetResult() {
        _result.value = 0.0
    }
}