package com.example.weightofring


import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.weightofring.GemDrawablesStore.getGemDrawable
import com.example.weightofring.domain.model.CutType
import com.example.weightofring.domain.model.GemParameters

class CalculateGemViewModel : ViewModel() {

    private val _allGemParameters = MutableLiveData<List<GemParameters>>(
        listOf(
            GemParameters(nameGem = "Diamond", densityGem = "3.52", nameEnum = GemParameters.NameGemEnum.DIAMOND),
            GemParameters(nameGem = "Rubin", densityGem = "3.99", nameEnum = GemParameters.NameGemEnum.RUBIN),
            GemParameters(nameGem = "Emerald", densityGem = "2.71", nameEnum = GemParameters.NameGemEnum.EMERALD),
            GemParameters(nameGem = "Сitrine", densityGem = "2.65", nameEnum = GemParameters.NameGemEnum.CITRINE),
            GemParameters(nameGem = "Amethyst", densityGem = "2.65", nameEnum = GemParameters.NameGemEnum.AMETHYST),
            GemParameters(nameGem = "Aquamarine", densityGem = "2.69", nameEnum = GemParameters.NameGemEnum.AQUAMARINE)
        )
    )
    val allGemParameters: LiveData<List<GemParameters>> get() = _allGemParameters

    private val _allCutParameters = MutableLiveData(
        listOf(
            CutType(name = "Round", form = CutType.CutForm.ROUND, calculationCoefficient = "0.0018"),
            CutType(name = "Princess", form = CutType.CutForm.PRINCESS, calculationCoefficient = "0.0023"),
            CutType(name = "Oval", form = CutType.CutForm.OVAL, calculationCoefficient = "0.0018"),
            CutType(name = "Emerald", form = CutType.CutForm.EMERALD, calculationCoefficient = "0.00245"),
            CutType(name = "Baguette", form = CutType.CutForm.BAGUETTE, calculationCoefficient = "0.0029"),
            CutType(name = "Marquis", form = CutType.CutForm.MARQUIS, calculationCoefficient = "0.0016")
        )
    )
    val allCutParameters: LiveData<List<CutType>> get() = _allCutParameters

    private val _gemImage = MutableLiveData<Int>()
    val gemImage: LiveData<Int> get() = _gemImage

    private val _gemSpinnerPosition = MutableLiveData(0)
    val gemSpinnerPosition: LiveData<Int> get() = _gemSpinnerPosition

    private val _cutSpinnerPosition = MutableLiveData(0)
    val cutSpinnerPosition: LiveData<Int> get() = _cutSpinnerPosition

    private val _lengthGem = MutableLiveData("")
    val lengthGem: LiveData<String> get() = _lengthGem

    private val _widthGem = MutableLiveData("")
    val widthGem: LiveData<String> get() = _widthGem

    private val _depthGem = MutableLiveData("")
    val depthGem: LiveData<String> get() = _depthGem


    private fun updateImageView() {
        val gemPosition = _gemSpinnerPosition.value
        val cutPosition = _cutSpinnerPosition.value
        val listGem = _allGemParameters.value
        val listCut = _allCutParameters.value

        if (gemPosition != null && cutPosition != null && listGem != null && listCut != null) {
            val imgRes = getGemDrawable(
                listGem[gemPosition].nameEnum,
                listCut[cutPosition].form,
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
}

