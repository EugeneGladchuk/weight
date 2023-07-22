package com.example.weightofring.ui.fragments.calculate_wire_fragment.model

data class TypeCalculateForWire (
    val typeCalculateName: String,
    val typeCalculateWireEnum: TypeCalculateWireEnum
) {
    override fun toString(): String {
        return typeCalculateName
    }
}