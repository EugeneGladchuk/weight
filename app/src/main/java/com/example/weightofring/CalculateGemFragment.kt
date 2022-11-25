package com.example.weightofring

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.example.weightofring.databinding.FragmentCalculateGemBinding
import com.example.weightofring.domain.model.CutType
import com.example.weightofring.domain.model.GemParameters


class CalculateGemFragment : Fragment() {

    private val viewModel: CalculateGemViewModel by viewModels()

    lateinit var binding: FragmentCalculateGemBinding

    lateinit var adapter: ArrayAdapter<GemParameters>
    lateinit var adapterCut: ArrayAdapter<CutType>

    private val gemTypeListener = object : AdapterView.OnItemSelectedListener {
        override fun onItemSelected(p0: AdapterView<*>?, view: View?, position: Int, id: Long) {
            viewModel.gemSpinnerChanged(position)
        }
        override fun onNothingSelected(position: AdapterView<*>?) {
        }
    }
    private val cutTypeListener = object : AdapterView.OnItemSelectedListener {
        override fun onItemSelected(p0: AdapterView<*>?, view: View?, position: Int, id: Long) {
            viewModel.cutSpinnerChanged(position)
        }
        override fun onNothingSelected(position: AdapterView<*>?) {
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        binding = FragmentCalculateGemBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        viewModel.allGemParameters.observe(viewLifecycleOwner) {
            setupListGemParameters(it)
        }

        viewModel.allCutParameters.observe(viewLifecycleOwner) {
            setupListCutParameters(it)
        }

        viewModel.gemSpinnerPosition.observe(viewLifecycleOwner) {
            if (it != binding.gemSpinner.selectedItemPosition) {
                binding.gemSpinner.setSelection(it)
            }
        }
        viewModel.cutSpinnerPosition.observe(viewLifecycleOwner) {
            if (it != binding.cutSpinner.selectedItemPosition) {
                binding.cutSpinner.setSelection(it)
            }
        }

        viewModel.gemImage.observe(viewLifecycleOwner) {
            binding.imageView.setImageResource(it)
        }

        binding.buttonResultGem.setOnClickListener {
//            gemWeightResult()
        }

        //viewModel.listOfGemParameters -> вызываем setupListParameters(list)

//        binding.buttonResultGem.setOnClickListener {
//            if (binding.lengthEditText.text.isBlank()) {
//                binding.lengthEditText.error = ("empty")
//            } else if (binding.widthEditText.text.isBlank()) {
//                binding.widthEditText.error = ("empty")
//            } else if (binding.depthEditText.text.isBlank()) {
//                binding.depthEditText.error = ("empty")
//            } else {
//                gemWeightResult()
//            }
//        }
    }

    private fun setupListGemParameters(list: List<GemParameters>) {
        adapter = ArrayAdapter(
            requireContext(),
            android.R.layout.simple_list_item_1,
            android.R.id.text1,
            list
        )
        binding.gemSpinner.adapter = adapter
        binding.gemSpinner.onItemSelectedListener = gemTypeListener
    }

    private fun setupListCutParameters(list: List<CutType>) {
        adapterCut = ArrayAdapter(
            requireContext(),
            android.R.layout.simple_list_item_1,
            android.R.id.text1,
            list
        )
        binding.cutSpinner.adapter = adapterCut
        binding.cutSpinner.onItemSelectedListener = cutTypeListener
    }

//    private fun gemWeightResult() {
//
//        val lengthGem = binding.lengthEditText.text.toString().toDouble()
//        val widthGem = binding.widthEditText.text.toString().toDouble()
//        val depthGem = binding.depthEditText.text.toString().toDouble()
//        val densityGemCalculate = viewModel.allGemParameters.densityGem.toDouble()
//        val typeCutCalculate = selectedCutParameters.calculationCoefficient.toDouble()
//
//        val sizeGem: Double
//        val sizePrincess = (lengthGem + widthGem) / 2
//
//        if (selectedCutParameters.form == CutType.CutForm.OVAL) {
//            sizeGem = sizePrincess * sizePrincess * depthGem
//        } else {
//            sizeGem = lengthGem * widthGem * depthGem
//        }
//
//        val gemDensity = sizeGem * 2 /*densityGemCalculate*/
//        val gemWeightResult = gemDensity * typeCutCalculate
//        val gemWeightResultFloor = floor(gemWeightResult * 1000.0) / 1000.0
//
//        val gemWeightGramms = gemWeightResult * 0.2
//        val gemWeightGrammsFloor = floor(gemWeightGramms * 1000.0) / 1000.0
//
//        gemWeightResultFloor.toString().also { binding.textViewResult.text = it }
//        gemWeightGrammsFloor.toString().also { binding.resultGemGrammTV.text = it }
//
//    }


    companion object {

        const val GEM = "CalculateGemFragment"

        @JvmStatic
        fun newInstance() = CalculateGemFragment()
    }
}