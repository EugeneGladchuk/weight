package com.example.weightofring.ui.fragments.gem_fragment

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.weightofring.data.database.gemresult.GemResult
import com.example.weightofring.di.factory.GemRepositoryFactory
import com.example.weightofring.domain.GemDrawablesStore.getGemDrawable
import com.example.weightofring.domain.reposytories.GemRepository
import com.example.weightofring.ui.fragments.gem_fragment.model.GemParametersEnum
import com.example.weightofring.domain.model.Lists
import com.example.weightofring.domain.use_case.CalculateGemWeightUseCase
import com.example.weightofring.ui.fragments.gem_fragment.model.CutFormEnum
import kotlinx.coroutines.launch
import java.lang.Math.floor

class CalculateGemViewModel(application: Application) : AndroidViewModel(application) {

    private val repository: GemRepository = GemRepositoryFactory.getGemRepository(application)

    private val calculateGemWeightUseCase = CalculateGemWeightUseCase()

    data class CutEditTextState(
        val text: String,
        val error: Boolean
    )

    val myDataList: LiveData<List<GemResult>> = repository.getAllGemResult()

    private val _allGemParameters = MutableLiveData(Lists.listGemParameters)
    val allGemParameters: LiveData<List<GemParametersEnum>> get() = _allGemParameters

    private val _allCutParameters = MutableLiveData(Lists.listCutParameters)
    val allCutParameters: LiveData<List<CutFormEnum>> get() = _allCutParameters

    private val _gemImage = MutableLiveData<Int>()
    val gemImage: LiveData<Int> get() = _gemImage

    private val _gemSpinnerPosition = MutableLiveData(0)
    val gemSpinnerPosition: LiveData<Int> get() = _gemSpinnerPosition

    private val _cutSpinnerPosition = MutableLiveData(0)
    val cutSpinnerPosition: LiveData<Int> get() = _cutSpinnerPosition

    private val _lengthGem = MutableLiveData(CutEditTextState(text = "", error = false))
    val lengthGem: LiveData<CutEditTextState> get() = _lengthGem

    private val _widthGem = MutableLiveData(CutEditTextState(text = "", error = false))
    val widthGem: LiveData<CutEditTextState> get() = _widthGem

    private val _depthGem = MutableLiveData(CutEditTextState(text = "", error = false))
    val depthGem: LiveData<CutEditTextState> get() = _depthGem

    private val _resultCarat = MutableLiveData<Double>(0.0)
    val resultCarat: LiveData<Double> get() = _resultCarat

    private val _resultGram = MutableLiveData<Double>(0.0)
    val resultGram: LiveData<Double> get() = _resultGram

    private fun checkAllValues() {

        var lengthGemDouble = 0.0
        try {
            val lengthGem = _lengthGem.value ?: throw Throwable("INVALID_SIZE")
            lengthGemDouble = lengthGem.text.toDouble()
        } catch (ex: Exception) {
            _lengthGem.value = _lengthGem.value?.copy(error = true)
        }
        var widthGemDouble = 0.0
        try {
            val widthGem = _widthGem.value ?: throw Throwable("INVALID_SIZE")
            widthGemDouble = widthGem.text.toDouble()
        } catch (ex: Exception) {
            _widthGem.value = _widthGem.value?.copy(error = true)
        }

        var depthGemDouble = 0.0
        try {
            val depthGem = _depthGem.value ?: throw Throwable("INVALID_SIZE")
            depthGemDouble = depthGem.text.toDouble()
        } catch (ex: Exception) {
            _depthGem.value = _depthGem.value?.copy(error = true)
        }

        val gemPosition = _gemSpinnerPosition.value!!
        val cutPosition = _cutSpinnerPosition.value!!
        val listGem = _allGemParameters.value!!
        val listCut = _allCutParameters.value!!

        val resultByCarat = calculateGemWeightUseCase.calculateGem(
            lengthGemDouble,
            widthGemDouble,
            depthGemDouble,
            gemPosition,
            cutPosition,
            listGem,
            listCut
        )
        val resultByGram = floor((resultByCarat * 0.2) * 1000.0) / 1000.0

        if (_lengthGem.value?.error == true || _widthGem.value?.error == true || _depthGem.value?.error == true) {
            _resultCarat.value = 0.0
            _resultGram.value = 0.0
        } else {
            _resultCarat.value = resultByCarat
            _resultGram.value = resultByGram
            saveToDatabase(
                listCut[cutPosition].name,
                listGem[gemPosition].toString(),
                lengthGemDouble,
                widthGemDouble,
                depthGemDouble,
                resultByCarat,
                resultByGram
            )
        }
    }

    private fun saveToDatabase(name: String,
                               nameGem: String,
                               lengthGemDouble: Double,
                               widthGemDouble: Double,
                               depthGemDouble: Double,
                               resultByCarat: Double,
                               resultByGram: Double) {
        viewModelScope.launch {
            val gemResult = GemResult(
                null,
                name,
                nameGem,
                lengthGemDouble.toString(),
                widthGemDouble.toString(),
                depthGemDouble.toString(),
                resultByCarat.toString(),
                resultByGram.toString())
            repository.saveGameToDatabase(gemResult)
        }
    }

    fun deleteButtonClick(item: GemResult) {
        viewModelScope.launch {
            repository.deleteGemResult(item)
        }
    }

    private fun updateImageView() {
        val gemPosition = _gemSpinnerPosition.value
        val cutPosition = _cutSpinnerPosition.value
        val listGem = _allGemParameters.value
        val listCut = _allCutParameters.value

        if (gemPosition != null && cutPosition != null && listGem != null && listCut != null) {
            val imgRes = getGemDrawable(
                listGem[gemPosition],
                listCut[cutPosition]
            )
            imgRes?.let {
                _gemImage.value = it
            }
        }
    }

    fun gemSpinnerChanged(gemSpinnerPosition: Int) {
        updateGemSpinner(gemSpinnerPosition)
        updateImageView()
    }

    private fun updateGemSpinner(gemSpinnerPosition: Int) {
        if (_gemSpinnerPosition.value != gemSpinnerPosition) {
            _gemSpinnerPosition.value = gemSpinnerPosition
        }
    }

    fun cutSpinnerChanged(cutSpinnerPosition: Int) {
        updateCutSpinner(cutSpinnerPosition)
        updateImageView()
    }

    private fun updateCutSpinner(cutSpinnerPosition: Int) {
        if (_cutSpinnerPosition.value != cutSpinnerPosition) {
            _cutSpinnerPosition.value = cutSpinnerPosition
        }
    }

    fun lengthTextChanged(length: String) {
        updateLength(length)
    }

    private fun updateLength(length: String) {
        if (_lengthGem.value?.text != length) {
            _lengthGem.value = _lengthGem.value?.copy(text = length, error = false)
        }
    }

    fun widthTextChanged(length: String) {
        updateWidth(length)
    }

    private fun updateWidth(width: String) {
        if (_widthGem.value?.text != width) {
            _widthGem.value = _widthGem.value?.copy(text = width, error = false)
        }
    }

    fun depthTextChanged(depth: String) {
        updateDepth(depth)
    }

    private fun updateDepth(depth: String) {
        if (_depthGem.value?.text != depth) {
            _depthGem.value = _depthGem.value?.copy(text = depth, error = false)
        }
    }

    fun onButtonResultClicked() {
        checkAllValues()
    }
}

