package com.example.weightofring.data.network

data class GoldPriceApi(
    val success: Boolean,
    val timestamp: Long,
    val date: String,
    val base: String,
    val rates: RatesApi
    )

