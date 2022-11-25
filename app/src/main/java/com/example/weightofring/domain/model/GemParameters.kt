package com.example.weightofring.domain.model

class GemParameters(
    val nameGem: String,
    val densityGem: String,
    val nameEnum: NameGemEnum
) {
    override fun toString(): String {
        return nameGem
    }

    enum class NameGemEnum {
        DIAMOND,
        RUBIN,
        EMERALD,
        CITRINE,
        AMETHYST,
        AQUAMARINE
    }
}
