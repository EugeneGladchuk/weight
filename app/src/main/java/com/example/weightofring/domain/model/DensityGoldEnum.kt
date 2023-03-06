package com.example.weightofring.domain.model

enum class DensityGoldEnum(var dens: Double, var typeName: String) {
    PLATINUM(0.0214, "PLATINUM"),
    GOLD_999(0.0194, "GOLD 999"),
    GOLD_750(0.0154, "GOLD 750"),
    GOLD_585(0.0138, "GOLD 585"),
    SILVER(0.01036, "SILVER")
}