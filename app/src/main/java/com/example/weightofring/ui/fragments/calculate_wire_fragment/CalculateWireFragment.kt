package com.example.weightofring.ui.fragments.calculate_wire_fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import androidx.core.widget.doOnTextChanged
import androidx.lifecycle.ViewModelProvider
import com.example.weightofring.R
import com.example.weightofring.databinding.FragmentCalculateWireBinding
import com.example.weightofring.ui.fragments.calculate_wire_fragment.model.TypeCalculateForWire
import com.example.weightofring.ui.fragments.ring_fragment.model.DensityGoldEnum

class CalculateWireFragment : Fragment() {

    lateinit var viewModel: CalculateWireViewModel

    lateinit var binding: FragmentCalculateWireBinding

    private lateinit var adapterWire: ArrayAdapter<TypeCalculateForWire>

    private val wireSpinnerListener = object : AdapterView.OnItemSelectedListener {
        override fun onItemSelected(p0: AdapterView<*>?, view: View?, position: Int, id: Long) {
            viewModel.wireSpinnerChanged(position)
        }

        override fun onNothingSelected(position: AdapterView<*>?) {
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentCalculateWireBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        viewModel = ViewModelProvider(requireActivity())[CalculateWireViewModel::class.java]

        binding.cardViewPlatinum.setOnClickListener { viewModel.updateTypeMetal(DensityGoldEnum.PLATINUM) }
        binding.cardViewGold999.setOnClickListener { viewModel.updateTypeMetal(DensityGoldEnum.GOLD_999) }
        binding.cardViewGold750.setOnClickListener { viewModel.updateTypeMetal(DensityGoldEnum.GOLD_750) }
        binding.cardViewGold585.setOnClickListener { viewModel.updateTypeMetal(DensityGoldEnum.GOLD_585) }
        binding.cardViewSilver.setOnClickListener { viewModel.updateTypeMetal(DensityGoldEnum.SILVER) }

        binding.editTextParameterOne.doOnTextChanged { text, start, before, count ->
            val newValue = if (text.isNullOrBlank()) "" else text.toString()
            viewModel.firstParameterChanged(newValue)
        }

        binding.editTextParameterTwo.doOnTextChanged { text, start, before, count ->
            val newValue = if (text.isNullOrBlank()) "" else text.toString()
            viewModel.secondParameterChanged(newValue)
        }

        binding.buttonResult.setOnClickListener {
            viewModel.onResultButtonClicked()
        }

        viewModel.listForSpinnerWire.observe(viewLifecycleOwner) {
            setupWireList(it)
        }

        viewModel.wireSpinnerPosition.observe(viewLifecycleOwner) {
            if (it != binding.wireSpinner.selectedItemPosition) {
                binding.wireSpinner.setSelection(it)
            }
            updateUi(it)
        }

        viewModel.typeMetal.observe(viewLifecycleOwner) { newTypeMetal ->
            allButtonTypeMetalIsAlpha()
            selectButtonTypeMetal(newTypeMetal)
        }

        viewModel.firstParameter.observe(viewLifecycleOwner) {
            if (it.text != binding.editTextParameterOne.text.toString()) {
                binding.editTextParameterOne.setText(it.text)
            }
            if (it.error) {
                binding.editTextParameterOne.error = "error"
            } else {
                binding.editTextParameterOne.error = null
            }
        }

        viewModel.secondParameter.observe(viewLifecycleOwner) {
            if (it.text != binding.editTextParameterTwo.text.toString()) {
                binding.editTextParameterTwo.setText(it.text)
            }
            if (it.error) {
                binding.editTextParameterTwo.error = "error"
            } else {
                binding.editTextParameterTwo.error = null
            }
        }

        viewModel.result.observe(viewLifecycleOwner) {
            if (it.toString() != binding.textViewResult.text) {
                binding.textViewResult.text = it.toString()
            }
        }

    }

    private fun setupWireList(list: MutableList<TypeCalculateForWire>) {
        adapterWire = ArrayAdapter(
            requireContext(),
            android.R.layout.simple_list_item_activated_1,
            android.R.id.text1,
            list
        )
        binding.wireSpinner.adapter = adapterWire
        binding.wireSpinner.onItemSelectedListener = wireSpinnerListener
    }

    private fun allButtonTypeMetalIsAlpha() {
        binding.cardViewPlatinum.alpha = 0.35F
        binding.cardViewGold999.alpha = 0.35F
        binding.cardViewGold750.alpha = 0.35F
        binding.cardViewGold585.alpha = 0.35F
        binding.cardViewSilver.alpha = 0.35F
    }

    private fun selectButtonTypeMetal(newTypeMetal: DensityGoldEnum) {
        when (newTypeMetal) {
            DensityGoldEnum.PLATINUM -> binding.cardViewPlatinum.alpha = 1F
            DensityGoldEnum.GOLD_999 -> binding.cardViewGold999.alpha = 1F
            DensityGoldEnum.GOLD_750 -> binding.cardViewGold750.alpha = 1F
            DensityGoldEnum.GOLD_585 -> binding.cardViewGold585.alpha = 1F
            else -> binding.cardViewSilver.alpha = 1F
        }
    }

    private fun updateUi(position: Int) {
        when (position) {
            0 -> {binding.textViewParameterOne.text = "ВЕС ЗАГОТОВКИ"
                binding.textViewParameterTwo.text = "ДИАМЕТР"
                binding.textViewDescription.setText(R.string.length_description)
                binding.textViewResultDescription.text = "ДЛИНА ПРВОЛОКИ"
                binding.textViewUnitResult.text = "мм"}

            1 -> {binding.textViewParameterOne.text = "ВЕС ЗАГОТОВКИ"
                binding.textViewParameterTwo.text = "ДЛИНА"
                binding.textViewDescription.setText(R.string.diameter_description)
                binding.textViewResultDescription.text = "ДИАМЕТР ПРВОЛОКИ"
                binding.textViewUnitResult.text = "мм"}

            2 -> {binding.textViewParameterOne.text = "ДИАМЕТР"
                binding.textViewParameterTwo.text = "ДЛИНА"
                binding.textViewDescription.setText(R.string.weight_description)
                binding.textViewResultDescription.text = "ВЕС ЗАГОТОВКИ"
                binding.textViewUnitResult.text = "грамм"}
        }
        viewModel.resetResult()
    }

    companion object {
        const val WIRE = "CalculateWireFragment"

        fun newInstance() = CalculateWireFragment()
    }
}