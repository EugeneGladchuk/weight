package com.example.weightofring.domain.use_case

import com.example.weightofring.ui.fragments.ring_fragment.model.DensityGoldEnum
import kotlin.math.PI
import kotlin.math.floor
import kotlin.math.sqrt

class CalculationsWireUseCase {

    fun calculateLengthWire(
        firstParameterDouble: Double,
        secondParameterDouble: Double,
        typeMetal: DensityGoldEnum
    ): Double {
        val volumeBase = firstParameterDouble / typeMetal.dens
        val cutArea = (secondParameterDouble * secondParameterDouble) / 4 * PI
        val result = (volumeBase / cutArea)
        return floor(result*100.0) / 100.0
    }

    fun calculateDiameterWire(
        firstParameterDouble: Double,
        secondParameterDouble: Double,
        typeMetal: DensityGoldEnum
    ): Double {
        val volumeBase = firstParameterDouble / typeMetal.dens
        val cutArea = volumeBase / secondParameterDouble
        val radius = sqrt(cutArea / PI)
        val result = radius * 2
        return floor(result*100.0) / 100.0
    }

    fun calculateWeightBaseForWire(
        firstParameterDouble: Double,
        secondParameterDouble: Double,
        typeMetal: DensityGoldEnum
    ): Double {
        val cutArea = (firstParameterDouble * firstParameterDouble) / 4 * PI
        val volumeWire = cutArea * secondParameterDouble
        val result = volumeWire * typeMetal.dens
        return floor(result*100.0) / 100.0
    }
}