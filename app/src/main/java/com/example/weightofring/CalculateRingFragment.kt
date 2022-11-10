package com.example.weightofring

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.widget.doOnTextChanged
import androidx.fragment.app.viewModels
import com.example.weightofring.databinding.FragmentCalculateRingBinding
import java.util.Observer

class CalculateRingFragment : Fragment() {

    private val viewModel: CalculateRingViewModel by viewModels()

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

        binding.editTextSizeRing.doOnTextChanged { text, start, before, count ->
//            Log.d(TAG, "editTextSizeRing: text = $text")
//            Log.d(TAG, "editTextSizeRing: start = $start")
//            Log.d(TAG, "editTextSizeRing: before = $before")
//            Log.d(TAG, "editTextSizeRing: count = $count")
            val newValue = if (text.isNullOrBlank()) " " else text.toString()
            viewModel.updateSize(newValue)
        }

        binding.editTextWidthRing.doOnTextChanged { text, start, before, count ->
            val newValue = if (text.isNullOrBlank()) " " else text.toString()
            viewModel.updateWidth(newValue)
        }

        binding.editTextThicknessRing.doOnTextChanged { text, start, before, count ->
            val newValue = if (text.isNullOrBlank()) " " else text.toString()
            viewModel.updateThickness(newValue)
        }

        viewModel.size.observe(viewLifecycleOwner) { newSize ->
            if (newSize != binding.editTextSizeRing.text.toString()){
                binding.editTextSizeRing.setText(newSize)
            }
        }

        viewModel.width.observe(viewLifecycleOwner) { newWidth ->
            if (newWidth != binding.editTextWidthRing.text.toString()){
                binding.editTextWidthRing.setText(newWidth)
            }
        }

        viewModel.thickness.observe(viewLifecycleOwner) { newThickness ->
            if (newThickness != binding.editTextThicknessRing.text.toString()){
                binding.editTextThicknessRing.setText(newThickness)
            }
        }

        binding.buttonResult.setOnClickListener {
            if (binding.editTextWidthRing.text.isBlank()) {
                binding.editTextWidthRing.error = ("empty")
            } else if (binding.editTextSizeRing.text.isBlank()) {
                binding.editTextSizeRing.error = ("empty")
            } else if (binding.editTextThicknessRing.text.isBlank()) {
                binding.editTextThicknessRing.error = ("empty")
            } else {
                displayResul()
            }
        }
    }

    private fun displayResul() {

        if (binding.radioButtonGold750.isChecked) {
            binding.textViewResult.text = viewModel.calculateWeightOfRing(
                viewModel.calculateVolumeOfRing(),
                DensityGoldEnum.GOLD_750
            ).toString()
        } else if (binding.radioButtonGold585.isChecked) {
            binding.textViewResult.text = viewModel.calculateWeightOfRing(
                viewModel.calculateVolumeOfRing(),
                DensityGoldEnum.GOLD_585
            ).toString()
        } else if (binding.radioButtonSilver.isChecked) {
            binding.textViewResult.text = viewModel.calculateWeightOfRing(
                viewModel.calculateVolumeOfRing(),
                DensityGoldEnum.SILVER
            ).toString()
        }
    }

    companion object {

        const val TAG = "OLOLO"
        const val RING = "CalculateRingFragment"

        @JvmStatic
        fun newInstance() = CalculateRingFragment()
    }
}