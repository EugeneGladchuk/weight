package com.example.weightofring.domain.model

class CutType(
    val name: String,
    val form: CutForm,
    val calculationCoefficient: String
) {
    override fun toString(): String {
        return name
    }

    enum class CutForm {
        ROUND,
        PRINCESS,
        OVAL,
        EMERALD,
        BAGUETTE,
        MARQUIS
    }
}