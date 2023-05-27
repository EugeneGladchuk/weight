package com.example.weightofring.domain.use_case

import com.example.weightofring.domain.model.GoldPriceForUi
import kotlin.math.floor

class CalculateExchangeRateUseCase {

    fun calculateExchange(
        fromPosition: Int,
        toPosition: Int,
        amount: Double,
        goldPrice: GoldPriceForUi
    ): String {

        val currencyFrom = goldPrice.currency[fromPosition]

        val currencyTo = goldPrice.currency[toPosition]

        val ratioToBaseCurrency = currencyTo / currencyFrom

        val result = ratioToBaseCurrency * amount

        val resultFloor = floor(result * 1000.0) / 1000.0

        return resultFloor.toString()
    }
}