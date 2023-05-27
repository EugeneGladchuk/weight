package com.example.weightofring.domain.model

data class GoldPriceForUi(
    val timestamp: Long,
    val metal: List<Double>,
    val currency: List<Double>
)
