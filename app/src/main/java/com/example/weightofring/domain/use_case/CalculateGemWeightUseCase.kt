package com.example.weightofring.domain.use_case

import com.example.weightofring.domain.model.CutType
import com.example.weightofring.domain.model.GemParameters
import java.lang.Math.floor

class CalculateGemWeightUseCase {

    fun calculateGem(
        lengthGemDouble: Double,
        widthGemDouble: Double,
        depthGemDouble: Double,
        gemPosition: Int,
        cutPosition: Int,
        listGem: List<GemParameters>,
        listCut: List<CutType>
    ): Double {

        val densityGemCalculate = listGem[gemPosition].densityGem.toDouble()
        val typeCutCalculate = listCut[cutPosition].calculationCoefficient.toDouble()

        val sizePrincess = (lengthGemDouble + widthGemDouble) / 2

        val sizeGem = if (listCut[cutPosition].form == CutType.CutForm.OVAL) {
            sizePrincess * sizePrincess * depthGemDouble
        } else {
            lengthGemDouble * widthGemDouble * depthGemDouble
        }

        val gemDensity = sizeGem * densityGemCalculate
        val result =  gemDensity * typeCutCalculate
        return floor(result * 1000.0) / 1000.0
    }
}