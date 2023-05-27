package com.example.weightofring.data.network

data class GoldPriceApi(
    val success: Boolean,
    val timestamp: Long,
    val base: String,
    val rates: RatesApi
    )

