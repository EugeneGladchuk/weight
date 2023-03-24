package com.example.weightofring.ui.fragments.ring_fragment

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.widget.doOnTextChanged
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.weightofring.ui.fragments.ring_fragment.model.DensityGoldEnum
import com.example.weightofring.R
import com.example.weightofring.databinding.FragmentCalculateRingBinding
import com.example.weightofring.ui.fragments.ring_fragment.model.TypeRing
import com.example.weightofring.ui.fragments.ring_fragment.RingListResultFragment.Companion.RING_LIST_RESULT

class CalculateRingFragment : Fragment() {

    lateinit var viewModel: CalculateRingViewModel

    private lateinit var binding: FragmentCalculateRingBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        binding = FragmentCalculateRingBinding.inflate(inflater, container, false)
        return binding.root
    }

    @SuppressLint("ResourceAsColor")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        viewModel = ViewModelProvider(requireActivity())[CalculateRingViewModel::class.java]


        binding.cardViewClassic.setOnClickListener { viewModel.updateTypeRing(TypeRing.CLASSIC)
            restoreResultButton()}
        binding.cardViewEuropean.setOnClickListener { viewModel.updateTypeRing(TypeRing.EUROPEAN)
            restoreResultButton()}


        binding.cardViewPlatinum.setOnClickListener { viewModel.updateTypeMetal(DensityGoldEnum.PLATINUM)
            restoreResultButton()}
        binding.cardViewGold999.setOnClickListener { viewModel.updateTypeMetal(DensityGoldEnum.GOLD_999)
            restoreResultButton()}
        binding.cardViewGold750.setOnClickListener { viewModel.updateTypeMetal(DensityGoldEnum.GOLD_750)
            restoreResultButton()}
        binding.cardViewGold585.setOnClickListener { viewModel.updateTypeMetal(DensityGoldEnum.GOLD_585)
            restoreResultButton()}
        binding.cardViewSilver.setOnClickListener { viewModel.updateTypeMetal(DensityGoldEnum.SILVER)
            restoreResultButton()}

        binding.editTextWidthRing.doOnTextChanged { text, start, before, count ->
            val newValue = if (text.isNullOrBlank()) "" else text.toString()
            viewModel.widthTextChanged(newValue)
            restoreResultButton()
        }

        binding.editTextSizeRing.doOnTextChanged { text, start, before, count ->
            val newValue = if (text.isNullOrBlank()) "" else text.toString()
            viewModel.sizeTextChanged(newValue)
            restoreResultButton()
        }

        binding.editTextThicknessRing.doOnTextChanged { text, start, before, count ->
            val newValue = if (text.isNullOrBlank()) "" else text.toString()
            viewModel.thicknessTextChanged(newValue)
            restoreResultButton()
        }

        binding.buttonResult.setOnClickListener {
            viewModel.onResultButtonClicked()
            checkEditText()
        }

        binding.buttonList.setOnClickListener {
            activity?.run {
                supportFragmentManager.beginTransaction()
                    .replace(R.id.frameLayout, RingListResultFragment.newInstance())
                    .addToBackStack(RING_LIST_RESULT)
                    .commit()
            }
        }

        viewModel.typeRing.observe(viewLifecycleOwner) { newTypeRing ->
            allButtonTypeRingIsAlpha()
            selectButtonTypeRing(newTypeRing)
        }

        viewModel.typeMetal.observe(viewLifecycleOwner) { newTypeMetal ->
            allButtonTypeMetalIsAlpha()
            selectButtonTypeMetal(newTypeMetal)
        }

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

        viewModel.result.observe(viewLifecycleOwner) {
            if (it.toString() != binding.textViewResult.text) {
                binding.textViewResult.text = it.toString()
            }
        }

        viewModel.lengthRingBase.observe(viewLifecycleOwner) {
            if (it.toString() != binding.textViewLengthBase.text) {
                binding.textViewLengthBase.text = it.toString()
            }
        }
    }

    private fun allButtonTypeRingIsAlpha() {
        binding.cardViewClassic.alpha = 0.35F
        binding.cardViewEuropean.alpha = 0.35F
    }

    private fun allButtonTypeMetalIsAlpha() {
        binding.cardViewPlatinum.alpha = 0.35F
        binding.cardViewGold999.alpha = 0.35F
        binding.cardViewGold750.alpha = 0.35F
        binding.cardViewGold585.alpha = 0.35F
        binding.cardViewSilver.alpha = 0.35F
    }

    private fun selectButtonTypeRing(newTypeRing: TypeRing) {
        when (newTypeRing) {
            TypeRing.CLASSIC -> binding.cardViewClassic.alpha = 1F
            TypeRing.EUROPEAN -> binding.cardViewEuropean.alpha = 1F
        }

        when (newTypeRing) {
            TypeRing.CLASSIC -> binding.exampleImage.setImageResource(R.drawable.classic_ring)
            TypeRing.EUROPEAN -> binding.exampleImage.setImageResource(R.drawable.european_ring)
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

    private fun checkEditText() {
        if (binding.editTextWidthRing.text.toString().isNotBlank()
            || binding.editTextSizeRing.text.toString().isNotBlank()
            || binding.editTextThicknessRing.text.toString().isNotBlank()
        ) {
            binding.buttonResult.isEnabled = false
            binding.cardViewButtonResult.alpha = 0.35F
        }
    }

    private fun restoreResultButton() {
        binding.buttonResult.isEnabled = true
        binding.cardViewButtonResult.alpha = 1F
    }

    companion object {

        const val RING = "CalculateRingFragment"

        fun newInstance() = CalculateRingFragment()
    }
}