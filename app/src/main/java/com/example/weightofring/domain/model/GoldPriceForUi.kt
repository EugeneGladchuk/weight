package com.example.weightofring.domain.model

data class GoldPriceForUi(
    val timestamp: Long,
    val updateTime: Long,
    val metal: List<Double>,
    val currency: List<Double>
)
