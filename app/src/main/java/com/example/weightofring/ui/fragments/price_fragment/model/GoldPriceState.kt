package com.example.weightofring.ui.fragments.price_fragment.model

sealed class GoldPriceState {
    object NoData: GoldPriceState()
    object Error: GoldPriceState()
    object Loading: GoldPriceState()
    object Success: GoldPriceState()
}