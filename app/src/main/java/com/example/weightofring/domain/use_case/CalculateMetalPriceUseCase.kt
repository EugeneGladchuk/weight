package com.example.weightofring.domain.use_case

import com.example.weightofring.domain.model.GoldPriceForUi
import com.example.weightofring.ui.fragments.price_fragment.model.UnitEnum
import kotlin.math.floor

class CalculateMetalPriceUseCase {

    fun calculateMetalPrice(
        metalPosition: Int,
        unitPosition: Int,
        unitsList: List<UnitEnum>,
        currencyPosition: Int,
        amountMetal: Double,
        goldPrice: GoldPriceForUi
    ): String {

        val metalPrice = goldPrice.metal[metalPosition]

        val units = amountMetal / unitsList[unitPosition].factor

        val currency = goldPrice.currency[0] * goldPrice.currency[currencyPosition]

        val result = units / metalPrice * currency

        val resultFloor = floor(result * 1000.0) / 1000.0

        return resultFloor.toString()
    }
}