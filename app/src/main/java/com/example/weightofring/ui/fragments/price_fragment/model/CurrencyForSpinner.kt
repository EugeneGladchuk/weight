package com.example.weightofring.ui.fragments.price_fragment.model

data class CurrencyForSpinner (
    val currencyName: String,
    val currencyNameEnum: CurrencyEnum
        ) {
    override fun toString(): String {
        return currencyName
    }
}
