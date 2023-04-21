package com.example.weightofring.ui.fragments.price_fragment.model

data class MetalForSpinner (
    val metalName: String,
    val metalNameEnum: MetalEnum
) {
    override fun toString(): String {
        return metalName
    }
}