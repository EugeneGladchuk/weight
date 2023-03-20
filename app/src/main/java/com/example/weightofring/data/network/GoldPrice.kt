package com.example.weightofring.data.network

data class Object(
 val XAU: Double,
 val BRONZE: Double,
 val XPT: Double,
 val XAG: Double,
 val XRH: Double,
 val RUTH: Double,
 val TIN: Double,
 val RUB: Double,
 val USD: Double,
 val EUR: Double,
 val AED: Double,
 val TRY: Double
)

data class GoldPrice(
 val success: Boolean,
 val timestamp: Long,
 val date: String,
 val base: String,
 val rates: Object
)