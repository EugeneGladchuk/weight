package com.example.weightofring

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.widget.doOnTextChanged
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.example.weightofring.databinding.FragmentCalculateRingBinding

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

        /* ############# События с View ############ */
        binding.editTextSizeRing.doOnTextChanged { text, start, before, count ->
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
        binding.radioGroup.setOnCheckedChangeListener { radioGroup, i ->
            when {
                binding.radioButtonGold750.isChecked -> viewModel.updateTypeMetal(DensityGoldEnum.GOLD_750)
                binding.radioButtonGold585.isChecked -> viewModel.updateTypeMetal(DensityGoldEnum.GOLD_585)
                binding.radioButtonSilver.isChecked -> viewModel.updateTypeMetal(DensityGoldEnum.SILVER)
            }
        }
        binding.buttonResult.setOnClickListener {
            viewModel.calculate()
        }

        /* ############## Подписки на ViewModel ############## */
        viewModel.size.observe(viewLifecycleOwner) {
            if (it.text != binding.editTextSizeRing.text.toString()) {
                binding.editTextSizeRing.setText(it.text)
            }
            if (it.error) {
                binding.editTextSizeRing.error = "error"
            } else {
                binding.editTextSizeRing.error = null
            }
        }
        viewModel.width.observe(viewLifecycleOwner) { newWidth ->
            if (newWidth != binding.editTextWidthRing.text.toString()) {
                binding.editTextWidthRing.setText(newWidth)
            }
        }
        viewModel.thickness.observe(viewLifecycleOwner) { newThickness ->
            if (newThickness != binding.editTextThicknessRing.text.toString()) {
                binding.editTextThicknessRing.setText(newThickness)
            }
        }
        viewModel.result.observe(viewLifecycleOwner) { newResult ->
            if (newResult != binding.textViewResult.text) {
                binding.textViewResult.text = newResult
            }
        }
        viewModel.typeMetal.observe(viewLifecycleOwner) { newTypeMetal ->
            val checkboxView = when (newTypeMetal) {
                DensityGoldEnum.GOLD_750 -> binding.radioButtonGold750
                DensityGoldEnum.GOLD_585 -> binding.radioButtonGold585
                else -> binding.radioButtonSilver
            }
            if (!checkboxView.isChecked) {
                checkboxView.isChecked = true
            }
        }
    }

    companion object {

        const val RING = "CalculateRingFragment"

        @JvmStatic
        fun newInstance() = CalculateRingFragment()
    }
}