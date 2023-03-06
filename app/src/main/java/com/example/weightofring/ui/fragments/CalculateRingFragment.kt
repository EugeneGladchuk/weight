package com.example.weightofring.ui.fragments

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.widget.doOnTextChanged
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.weightofring.ui.viewModels.CalculateRingViewModel
import com.example.weightofring.domain.model.DensityGoldEnum
import com.example.weightofring.R
import com.example.weightofring.databinding.FragmentCalculateRingBinding
import com.example.weightofring.ui.fragments.RingListResultFragment.Companion.RING_LIST_RESULT

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

        // убираем в VM
//        val db = AppDatabase.getDatabase(requireContext())

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
                binding.radioButtonPlatinum.isChecked -> viewModel.updateTypeMetal(DensityGoldEnum.PLATINUM)
                binding.radioButtonGold999.isChecked -> viewModel.updateTypeMetal(DensityGoldEnum.GOLD_999)
                binding.radioButtonGold750.isChecked -> viewModel.updateTypeMetal(DensityGoldEnum.GOLD_750)
                binding.radioButtonGold585.isChecked -> viewModel.updateTypeMetal(DensityGoldEnum.GOLD_585)
                binding.radioButtonSilver.isChecked -> viewModel.updateTypeMetal(DensityGoldEnum.SILVER)
            }
        }

        binding.buttonResult.setOnClickListener {
            viewModel.calculate()
            // Это выносим во вьюмодель, так как это логика
//            Thread {
//            db.ringResultDao().insertRingResult(viewModel.addToResultList())
//            }.start()
        }

        binding.buttonList.setOnClickListener {
            activity?.run {
                supportFragmentManager.beginTransaction()
                    .replace(R.id.frameLayout, RingListResultFragment.newInstance())
                    .addToBackStack(RING_LIST_RESULT)
                    .commit()
            }
        }

        /*viewModel.myDataList.observe(viewLifecycleOwner) {
            adapter.updateData(it)
        }*/

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

        viewModel.result.observe(viewLifecycleOwner) {
            if (it.toString() != binding.textViewResult.text) {
                binding.textViewResult.text = it.toString()
            }
        }

        viewModel.typeMetal.observe(viewLifecycleOwner) { newTypeMetal ->

            madeAllButtonAlpha()
            madeSelectedButton(newTypeMetal)

            val checkboxView = when (newTypeMetal) {
                DensityGoldEnum.PLATINUM -> binding.radioButtonPlatinum
                DensityGoldEnum.GOLD_999 -> binding.radioButtonGold999
                DensityGoldEnum.GOLD_750 -> binding.radioButtonGold750
                DensityGoldEnum.GOLD_585 -> binding.radioButtonGold585
                else -> binding.radioButtonSilver
            }
            if (!checkboxView.isChecked) {
                checkboxView.isChecked = true
            }
        }
    }

    private fun madeAllButtonAlpha() {
        binding.cardViewPlatinum.alpha = 0.35F
        binding.cardViewGold999.alpha = 0.35F
        binding.cardViewGold750.alpha = 0.35F
        binding.cardViewGold585.alpha = 0.35F
        binding.cardViewSilver.alpha = 0.35F

    }

    private fun madeSelectedButton(newTypeMetal: DensityGoldEnum) {
        when (newTypeMetal) {
            DensityGoldEnum.PLATINUM -> binding.cardViewPlatinum.alpha = 1F
            DensityGoldEnum.GOLD_999 -> binding.cardViewGold999.alpha = 1F
            DensityGoldEnum.GOLD_750 -> binding.cardViewGold750.alpha = 1F
            DensityGoldEnum.GOLD_585 -> binding.cardViewGold585.alpha = 1F
            else -> binding.cardViewSilver.alpha = 1F
        }
    }


    companion object {

        const val RING = "CalculateRingFragment"

        fun newInstance() = CalculateRingFragment()
    }
}