package com.example.weightofring

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.widget.doOnTextChanged
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.weightofring.databinding.FragmentCalculateRingBinding

class CalculateRingFragment : Fragment() {

//    private val viewModel: CalculateRingViewModel/* by viewModels()*/
    lateinit var viewModel: CalculateRingViewModel/* by viewModels()*/

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

        viewModel = ViewModelProvider(requireActivity())[CalculateRingViewModel::class.java]

        /* ############# События с View ############ */
        binding.editTextWidthRing.doOnTextChanged { text, start, before, count ->
            val newValue = if (text.isNullOrBlank()) " " else text.toString()
            viewModel.widthTextChanged(newValue)
        }

        binding.editTextSizeRing.doOnTextChanged { text, start, before, count ->
            val newValue = if (text.isNullOrBlank()) " " else text.toString()
            viewModel.sizeTextChanged(newValue)
        }

        binding.editTextThicknessRing.doOnTextChanged { text, start, before, count ->
            val newValue = if (text.isNullOrBlank()) " " else text.toString()
            viewModel.thicknessTextChanged(newValue)
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
        viewModel.width.observe(viewLifecycleOwner) {
            if (it.text != binding.editTextWidthRing.text.toString()) {
                binding.editTextWidthRing.setText(it.text)
            }
            if (it.error) {
                binding.editTextWidthRing.error = "error"
            } else {
                binding.editTextWidthRing.error = null
            }
        }

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

        viewModel.thickness.observe(viewLifecycleOwner) {
            if (it.text != binding.editTextThicknessRing.text.toString()) {
                binding.editTextThicknessRing.setText(it.text)
            }
            if (it.error) {
                binding.editTextThicknessRing.error = "error"
            } else {
                binding.editTextThicknessRing.error = null
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