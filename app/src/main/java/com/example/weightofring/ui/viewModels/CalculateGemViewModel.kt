package com.example.weightofring.ui.viewModels


import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.weightofring.domain.GemDrawablesStore.getGemDrawable
import com.example.weightofring.domain.model.CutType
import com.example.weightofring.domain.model.GemParameters
import java.lang.Math.floor

class CalculateGemViewModel : ViewModel() {

    data class CutEditTextState(
        val text: String,
        val error: Boolean
    )

    private val _allGemParameters = MutableLiveData<List<GemParameters>>(
        listOf(
            GemParameters(
                nameGem = "Diamond",
                densityGem = "3.52",
                nameEnum = GemParameters.NameGemEnum.DIAMOND
            ),
            GemParameters(
                nameGem = "Rubin",
                densityGem = "3.99",
                nameEnum = GemParameters.NameGemEnum.RUBIN
            ),
            GemParameters(
                nameGem = "Emerald",
                densityGem = "2.71",
                nameEnum = GemParameters.NameGemEnum.EMERALD
            ),
            GemParameters(
                nameGem = "Сitrine",
                densityGem = "2.65",
                nameEnum = GemParameters.NameGemEnum.CITRINE
            ),
            GemParameters(
                nameGem = "Amethyst",
                densityGem = "2.65",
                nameEnum = GemParameters.NameGemEnum.AMETHYST
            ),
            GemParameters(
                nameGem = "Aquamarine",
                densityGem = "2.69",
                nameEnum = GemParameters.NameGemEnum.AQUAMARINE
            )
        )
    )
    val allGemParameters: LiveData<List<GemParameters>> get() = _allGemParameters

    private val _allCutParameters = MutableLiveData(
        listOf(
            CutType(
                name = "Round",
                form = CutType.CutForm.ROUND,
                calculationCoefficient = "0.0018"
            ),
            CutType(
                name = "Princess",
                form = CutType.CutForm.PRINCESS,
                calculationCoefficient = "0.0023"
            ),
            CutType(
                name = "Oval",
                form = CutType.CutForm.OVAL,
                calculationCoefficient = "0.0018"
            ),
            CutType(
                name = "Emerald",
                form = CutType.CutForm.EMERALD,
                calculationCoefficient = "0.00245"
            ),
            CutType(
                name = "Baguette",
                form = CutType.CutForm.BAGUETTE,
                calculationCoefficient = "0.0029"
            ),
            CutType(
                name = "Marquis",
                form = CutType.CutForm.MARQUIS,
                calculationCoefficient = "0.0016"
            )
        )
    )
    val allCutParameters: LiveData<List<CutType>> get() = _allCutParameters

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

    private fun gemWeightResult() {

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

        val gemPosition = _gemSpinnerPosition.value
        val cutPosition = _cutSpinnerPosition.value
        val listGem = _allGemParameters.value
        val listCut = _allCutParameters.value

        if (gemPosition != null && cutPosition != null && listGem != null && listCut != null) {
            val densityGemCalculate = listGem[gemPosition].densityGem.toDouble()
            val typeCutCalculate = listCut[cutPosition].calculationCoefficient.toDouble()

            val sizeGem: Double
            val sizePrincess = (lengthGemDouble + widthGemDouble) / 2

            if (listCut[cutPosition].form == CutType.CutForm.OVAL) {
                sizeGem = sizePrincess * sizePrincess * depthGemDouble
            } else {
                sizeGem = lengthGemDouble * widthGemDouble * depthGemDouble
            }

            val gemDensity = sizeGem * densityGemCalculate
            val gemWeightResult = gemDensity * typeCutCalculate
            val resCarat = floor(gemWeightResult * 1000.0) / 1000.0

            val gemWeightGramms = gemWeightResult * 0.2
            val resGramm = floor(gemWeightGramms * 1000.0) / 1000.0

            if (_lengthGem.value?.error == true || _widthGem.value?.error == true || _depthGem.value?.error == true) {
                _resultCarat.value = 0.0
                _resultGram.value = 0.0
            } else {
                _resultCarat.value = resCarat
                _resultGram.value = resGramm
            }
        }
    }

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

    fun calculate() {
        gemWeightResult()
    }
}

