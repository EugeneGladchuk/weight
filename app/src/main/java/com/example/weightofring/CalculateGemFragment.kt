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
import kotlin.math.floor


class CalculateGemFragment : Fragment() {

    private val viewModel: CalculateRingViewModel by viewModels()

    lateinit var binding: FragmentCalculateGemBinding

    lateinit var adapter: ArrayAdapter<GemParameters>
    lateinit var adapterCut: ArrayAdapter<CutType>

    val gemParameters = mutableListOf(
        GemParameters(nameGem = "Diamond", densityGem = "3.52", nameEnum = GemParameters.NameGemEnum.DIAMOND),
        GemParameters(nameGem = "Rubin", densityGem = "3.99", nameEnum = GemParameters.NameGemEnum.RUBIN),
        GemParameters(nameGem = "Emerald", densityGem = "2.71", nameEnum = GemParameters.NameGemEnum.EMERALD),
        GemParameters(nameGem = "Сitrine", densityGem = "2.65", nameEnum = GemParameters.NameGemEnum.CITRINE),
        GemParameters(nameGem = "Amethyst", densityGem = "2.65", nameEnum = GemParameters.NameGemEnum.AMETHYST),
        GemParameters(nameGem = "Aquamarine", densityGem = "2.69", nameEnum = GemParameters.NameGemEnum.AQUAMARINE)
    )

    val cutType = mutableListOf(
        CutType(name = "Round", form = CutType.CutForm.ROUND, calculationCoefficient = "0.0018"),
        CutType(name = "Princess", form = CutType.CutForm.PRINCESS, calculationCoefficient = "0.0023"),
        CutType(name = "Oval", form = CutType.CutForm.OVAL, calculationCoefficient = "0.0018"),
        CutType(name = "Emerald", form = CutType.CutForm.EMERALD, calculationCoefficient = "0.00245"),
        CutType(name = "Baguette", form = CutType.CutForm.BAGUETTE, calculationCoefficient = "0.0029"),
        CutType(name = "Marquis", form = CutType.CutForm.MARQUIS, calculationCoefficient = "0.0016")
    )

    var selectedCutParameters: CutType = cutType[0]

    var selectedGemParameter: GemParameters = gemParameters[0]

    private val cutTypeListener = object : AdapterView.OnItemSelectedListener {
        override fun onItemSelected(p0: AdapterView<*>?, view: View?, position: Int, id: Long) {
            val differentTypeCut = cutType[position]
            selectedCutParameters = differentTypeCut

            val gemDrawable = GemDrawablesStore.getGemDrawable(selectedCutParameters.form, selectedGemParameter.nameEnum)

            if (gemDrawable != null) {
                binding.imageView.setImageResource(gemDrawable)
            }
        }
        override fun onNothingSelected(position: AdapterView<*>?) {
        }
    }

    private val gemTypeListener = object : AdapterView.OnItemSelectedListener {
        override fun onItemSelected(p0: AdapterView<*>?, view: View?, positionMat: Int, id: Long) {
            val differentDensity = gemParameters[positionMat]
            selectedGemParameter = differentDensity

            val gemDrawable = GemDrawablesStore.getGemDrawable(selectedCutParameters.form, selectedGemParameter.nameEnum)

             if (gemDrawable != null) {
                 binding.imageView.setImageResource(gemDrawable)
             }
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



        setupListParameters()
        setupListParameters2()

        binding.buttonResultGem.setOnClickListener {
            if (binding.lengthEditText.text.isBlank()) {
                binding.lengthEditText.error = ("empty")
            } else if (binding.widthEditText.text.isBlank()) {
                binding.widthEditText.error = ("empty")
            } else if (binding.depthEditText.text.isBlank()) {
                binding.depthEditText.error = ("empty")
            } else {
                gemWeightResult()
            }
        }
    }

    private fun setupListParameters() {
        adapter = ArrayAdapter(
            requireContext(),
            android.R.layout.simple_list_item_1,
            android.R.id.text1,
            gemParameters
        )
        binding.densitySpinner.adapter = adapter

        binding.densitySpinner.onItemSelectedListener = gemTypeListener
    }

    private fun setupListParameters2() {
        adapterCut = ArrayAdapter(
            requireContext(),
            android.R.layout.simple_list_item_1,
            android.R.id.text1,
            cutType
        )
        binding.cutSpinner.adapter = adapterCut

        binding.cutSpinner.onItemSelectedListener = cutTypeListener
    }

    private fun gemWeightResult() {

        val lengthGem = binding.lengthEditText.text.toString().toDouble()
        val widthGem = binding.widthEditText.text.toString().toDouble()
        val depthGem = binding.depthEditText.text.toString().toDouble()
        val densityGemCalculate = selectedGemParameter.densityGem.toDouble()
        val typeCutCalculate = selectedCutParameters.calculationCoefficient.toDouble()

        val sizeGem: Double
        val sizePrincess = (lengthGem + widthGem) / 2

        if (selectedCutParameters.form == CutType.CutForm.OVAL) {
            sizeGem = sizePrincess * sizePrincess * depthGem
        } else {
            sizeGem = lengthGem * widthGem * depthGem
        }

        val gemDensity = sizeGem * densityGemCalculate
        val gemWeightResult = gemDensity * typeCutCalculate
        val gemWeightResultFloor = floor(gemWeightResult * 1000.0) / 1000.0

        val gemWeightGramms = gemWeightResult * 0.2
        val gemWeightGrammsFloor = floor(gemWeightGramms * 1000.0) / 1000.0

        gemWeightResultFloor.toString().also { binding.textViewResult.text = it }
        gemWeightGrammsFloor.toString().also { binding.resultGemGrammTV.text = it }

    }

    class GemParameters(
        val nameGem: String,
        val densityGem: String,
        val nameEnum: NameGemEnum
    ) {
        override fun toString(): String {
            return nameGem
        }

        enum class NameGemEnum {
            DIAMOND,
            RUBIN,
            EMERALD,
            CITRINE,
            AMETHYST,
            AQUAMARINE
        }
    }

    class CutType(
        val name: String,
        val form: CutForm,
        val calculationCoefficient: String
    ) {
        override fun toString(): String {
            return name
        }

        enum class CutForm {
            ROUND,
            PRINCESS,
            OVAL,
            EMERALD,
            BAGUETTE,
            MARQUIS
        }
    }

    companion object {

        const val GEM = "CalculateGemFragment"

        @JvmStatic
        fun newInstance() = CalculateGemFragment()
    }
}