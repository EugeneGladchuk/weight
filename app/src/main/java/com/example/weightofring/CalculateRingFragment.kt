package com.example.weightofring

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.weightofring.databinding.FragmentCalculateRingBinding
import kotlin.math.floor


class CalculateRingFragment : Fragment() {

    private lateinit var binding: FragmentCalculateRingBinding


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        binding = FragmentCalculateRingBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

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
        val size = binding.editTextSizeRing.text.toString().toDouble()
        val width = binding.editTextWidthRing.text.toString().toDouble()
        val thickness = binding.editTextThicknessRing.text.toString().toDouble()
        val areaSize = ((size * size)/4)*3.14
        val insideDiameter = (thickness * 2) + size
        val areaTotal = ((insideDiameter * insideDiameter)/4)*3.14
        val areaRingSide = areaTotal - areaSize
        val volumeRing = areaRingSide * width
        val gold750 = 0.0154 * volumeRing
        val gold750Floor = floor(gold750 * 100.0) /100.0
        val gold585 = 0.0138 * volumeRing
        val gold585Floor = floor(gold585 * 100.0) /100.0
        val silver = 0.01036 * volumeRing
        val silverFloor = floor(silver * 100.0) /100.0


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

    companion object {

        const val RING = "CalculateRingFragment"

        @JvmStatic
        fun newInstance() = CalculateRingFragment()
    }
}