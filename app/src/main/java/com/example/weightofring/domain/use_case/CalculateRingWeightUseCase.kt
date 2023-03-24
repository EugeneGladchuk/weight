package com.example.weightofring.domain.use_case

import com.example.weightofring.ui.fragments.ring_fragment.model.DensityGoldEnum
import com.example.weightofring.ui.fragments.ring_fragment.model.TypeRing
import kotlin.math.PI
import kotlin.math.floor

class CalculateRingWeightUseCase {

    fun calculateRing(
        typeRing: String,
        widthDouble: Double,
        sizeDouble: Double,
        thicknessDouble: Double,
        typeMetal: DensityGoldEnum
    ): Double {

        val ringVolume = calculateLengthBase(sizeDouble, thicknessDouble) * calculateCutArea(
            widthDouble, thicknessDouble, typeRing
        )

        val resultFloor = (ringVolume * typeMetal.dens)

        return floor(resultFloor * 100.0).div(100.0)
    }

    fun calculateLengthBase(sizeDouble: Double, thicknessDouble: Double): Double {
        val middleDiameter = sizeDouble + thicknessDouble
        return middleDiameter * PI
    }

    private fun calculateCutArea(width: Double, thickness: Double, typeRing: String): Double {
        val europeanCutArea = width * thickness
        val classicCutArea = ((width / 2) * thickness * 3.14) / 2
        return if (typeRing == TypeRing.CLASSIC.toString()) {
            classicCutArea
        } else {
            europeanCutArea
        }
    }
}