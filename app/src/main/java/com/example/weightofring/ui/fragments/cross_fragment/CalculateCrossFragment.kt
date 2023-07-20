package com.example.weightofring.ui.fragments.cross_fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.core.widget.doOnTextChanged
import androidx.lifecycle.ViewModelProvider
import com.example.weightofring.R
import com.example.weightofring.databinding.FragmentCalculateCrossBinding
import com.example.weightofring.ui.fragments.cross_fragment.model.TypeCross
import com.example.weightofring.ui.fragments.ring_fragment.model.DensityGoldEnum


class CalculateCrossFragment : Fragment() {

    lateinit var viewModel: CalculateCrossViewModel

    lateinit var binding: FragmentCalculateCrossBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentCalculateCrossBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        viewModel = ViewModelProvider(requireActivity())[CalculateCrossViewModel::class.java]

        binding.cardViewSquareCross.setOnClickListener { viewModel.updateTypeCross(TypeCross.SQUARE) }
        binding.cardViewRoundCross.setOnClickListener { viewModel.updateTypeCross(TypeCross.ROUND) }

        binding.cardViewPlatinum.setOnClickListener { viewModel.updateTypeMetal(DensityGoldEnum.PLATINUM) }
        binding.cardViewGold999.setOnClickListener { viewModel.updateTypeMetal(DensityGoldEnum.GOLD_999) }
        binding.cardViewGold750.setOnClickListener { viewModel.updateTypeMetal(DensityGoldEnum.GOLD_750) }
        binding.cardViewGold585.setOnClickListener { viewModel.updateTypeMetal(DensityGoldEnum.GOLD_585) }
        binding.cardViewSilver.setOnClickListener { viewModel.updateTypeMetal(DensityGoldEnum.SILVER) }

        binding.editTextLength.doOnTextChanged { text, start, before, count ->
            val newValue = if (text.isNullOrBlank()) "" else text.toString()
            viewModel.lengthTextChanged(newValue)
        }

        binding.editTextWidth.doOnTextChanged { text, start, before, count ->
            val newValue = if (text.isNullOrBlank()) "" else text.toString()
            viewModel.widthTextChanged(newValue)
        }

        binding.editTextThickness.doOnTextChanged { text, start, before, count ->
            val newValue = if (text.isNullOrBlank()) "" else text.toString()
            viewModel.thicknessTextChanged(newValue)
        }

        binding.editTextRayWidth.doOnTextChanged { text, start, before, count ->
            val newValue = if (text.isNullOrBlank()) "" else text.toString()
            viewModel.rayWidthTextChanged(newValue)
        }

        binding.buttonResult.setOnClickListener {
            viewModel.onResultButtonClicked()
        }

        viewModel.typeCross.observe(viewLifecycleOwner) { newTypeCross ->
            allButtonTypeCrossIsAlpha()
            selectButtonTypeCross(newTypeCross)
        }

        viewModel.typeMetal.observe(viewLifecycleOwner) { newTypeMetal ->
            allButtonTypeMetalIsAlpha()
            selectButtonTypeMetal(newTypeMetal)
        }

        viewModel.length.observe(viewLifecycleOwner) {
            if (it.text != binding.editTextLength.text.toString()) {
                binding.editTextLength.setText(it.text)
            }
            if (it.error) {
                binding.editTextLength.error = "error"
            } else {
                binding.editTextLength.error = null
            }
        }

        viewModel.width.observe(viewLifecycleOwner) {
            if (it.text != binding.editTextWidth.text.toString()) {
                binding.editTextWidth.setText(it.text)
            }
            if (it.error) {
                binding.editTextWidth.error = "error"
            } else {
                binding.editTextWidth.error = null
            }
        }

        viewModel.thickness.observe(viewLifecycleOwner) {
            if (it.text != binding.editTextThickness.text.toString()) {
                binding.editTextThickness.setText(it.text)
            }
            if (it.error) {
                binding.editTextThickness.error = "error"
            } else {
                binding.editTextThickness.error = null
            }
        }

        viewModel.rayWidth.observe(viewLifecycleOwner) {
            if (it.text != binding.editTextRayWidth.text.toString()) {
                binding.editTextRayWidth.setText(it.text)
            }
            if (it.error) {
                binding.editTextRayWidth.error = "error"
            } else {
                binding.editTextRayWidth.error = null
            }
        }

        viewModel.result.observe(viewLifecycleOwner) {
            if (it.toString() != binding.textViewResult.text) {
                binding.textViewResult.text = it.toString()
            }
        }
    }

    private fun allButtonTypeCrossIsAlpha() {
        binding.cardViewSquareCross.alpha = 0.35F
        binding.cardViewRoundCross.alpha = 0.35F
    }

    private fun allButtonTypeMetalIsAlpha() {
        binding.cardViewPlatinum.alpha = 0.35F
        binding.cardViewGold999.alpha = 0.35F
        binding.cardViewGold750.alpha = 0.35F
        binding.cardViewGold585.alpha = 0.35F
        binding.cardViewSilver.alpha = 0.35F
    }

    private fun selectButtonTypeCross(newTypeCross: TypeCross) {
        when (newTypeCross) {
            TypeCross.SQUARE -> {binding.cardViewSquareCross.alpha = 1F
                binding.cardViewRayWidth.isVisible = true
                binding.textViewThickness.text = "ТОЛЩИНА"}
            TypeCross.ROUND -> {binding.cardViewRoundCross.alpha = 1F
                binding.cardViewRayWidth.isVisible = false
                binding.textViewThickness.text = "ДИАМЕТР"}
        }

        when (newTypeCross) {
            TypeCross.SQUARE -> binding.exampleImage.setImageResource(R.drawable.squarecross)
            TypeCross.ROUND -> binding.exampleImage.setImageResource(R.drawable.roundcross)
        }
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

    companion object {
        const val CROSS = "CalculateCrossFragment"

        fun newInstance() = CalculateCrossFragment()
    }
}