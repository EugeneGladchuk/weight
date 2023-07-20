package com.example.weightofring.domain.use_case

import com.example.weightofring.ui.fragments.ring_fragment.model.DensityGoldEnum
import kotlin.math.PI
import kotlin.math.floor

class CalculateCrossWeightUseCase {

    fun calculateSquareCross(
        lengthDouble: Double,
        widthDouble: Double,
        thicknessDouble: Double,
        rayWidthDouble: Double,
        typeMetal: DensityGoldEnum
    ): Double {
        val areaVerticalRay = lengthDouble * rayWidthDouble
        val areaHorizontalRay = (widthDouble - rayWidthDouble) * rayWidthDouble
        val volumeCross = (areaVerticalRay + areaHorizontalRay) * thicknessDouble
        val result = volumeCross * typeMetal.dens
        return floor(result*100.0) / 100.0
    }

    fun calculateRoundCross(
        lengthDouble: Double,
        widthDouble: Double,
        thicknessDouble: Double,
        typeMetal: DensityGoldEnum
    ): Double {
        val circleArea = (thicknessDouble * thicknessDouble) / 4 * PI
        val booleanPath = thicknessDouble / 6 * 5
        val totalLength = lengthDouble + widthDouble - booleanPath
        val result = totalLength * circleArea * typeMetal.dens
        return floor(result*100.0) / 100.0
    }
}