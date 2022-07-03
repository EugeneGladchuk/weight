package com.example.weightofring

import android.app.Activity
import android.os.Bundle
import com.example.weightofring.databinding.ActivityCalculateBinding
import kotlin.math.floor

class Calculate : Activity() {

    lateinit var binding: ActivityCalculateBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCalculateBinding.inflate(layoutInflater)
        setContentView(binding.root)



        binding.buttonResult.setOnClickListener {
            if (binding.editTextWidthRing.text.isBlank()) {
                binding.editTextWidthRing.error = ("empty")
            } else if (binding.editTextSizeRing.text.isBlank()) {
                binding.editTextSizeRing.error = ("empty")
            } else if (binding.editTextThicknessRing.text.isBlank()) {
                binding.editTextThicknessRing.error = ("empty")
            } else {
                resultview()
            }

        }



    }

    private fun resultview(): Boolean {
        var size = binding.editTextSizeRing.text.toString().toDouble()
        var width = binding.editTextWidthRing.text.toString().toDouble()
        var thickness = binding.editTextThicknessRing.text.toString().toDouble()

        var areaSize = ((size * size)/4)*3.14
        var insideDiameter = (thickness * 2) + size
        var areaTotal = ((insideDiameter * insideDiameter)/4)*3.14
        var areaRingSide = areaTotal - areaSize
        var volumeRing = areaRingSide * width

        var gold750 = 0.0154 * volumeRing
        var gold750Floor = floor(gold750 * 100.0)/100.0
        var gold585 = 0.0138 * volumeRing
        var gold585Floor = floor(gold585 * 100.0)/100.0
        var silver = 0.01036 * volumeRing
        var silverFloor = floor(silver * 100.0)/100.0


        if (binding.radioButtonGold750.isChecked) {
            gold750Floor.toString().also { binding.textViewResult.text = it }
        }
        else if (binding.radioButtonGold585.isChecked) {
            gold585Floor.toString().also { binding.textViewResult.text = it }
        }
        else if (binding.radioButtonSilver.isChecked) {
            silverFloor.toString().also { binding.textViewResult.text = it }
        }
        return true
    }

}