package com.example.weightofring.data.network

data class Object(
 val AED: Double,
 val RUB: Double
)

data class GoldPrice(
 val success: Boolean,
 val timestamp: Long,
 val date: String,
 val base: String,
 val rates: Object
)