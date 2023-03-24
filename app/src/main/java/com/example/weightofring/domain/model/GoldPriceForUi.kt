package com.example.weightofring.domain.model

data class GoldPriceForUi(
    val timestamp: Long,
    val date: String,
    val metal: Metal,
    val currency: Currency
)
