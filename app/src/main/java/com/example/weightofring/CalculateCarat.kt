package com.example.weightofring

import android.app.Activity
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import com.example.weightofring.databinding.ActivityCalculateCaratBinding
import kotlin.math.floor



class CalculateCarat : Activity() {

    lateinit var binding: ActivityCalculateCaratBinding
    lateinit var adapter: ArrayAdapter<GemParameters>
    lateinit var adapterCut: ArrayAdapter<CutType>

    var imageArrayDiamond:IntArray = intArrayOf(
        R.drawable.rounddiamond,
        R.drawable.princdiamond,
        R.drawable.ovaldiamond,
        R.drawable.emerdiamond,
        R.drawable.bagdiamond,
        R.drawable.marcdiamond
    )
    var imageArrayRubin:IntArray = intArrayOf(
        R.drawable.roundrubin,
        R.drawable.princrub,
        R.drawable.ovalrubi,
        R.drawable.emerrubi,
        R.drawable.bagrubi,
        R.drawable.marcrubi
    )
    var imageArrayCitrin:IntArray = intArrayOf(
        R.drawable.roundcitrin,
        R.drawable.princcitrin,
        R.drawable.ovalcitrin,
        R.drawable.emercitrin,
        R.drawable.bagcitrin,
        R.drawable.marccitrin
    )
    var imageArrayEmerald:IntArray = intArrayOf(
        R.drawable.roundemerald,
        R.drawable.princemer,
        R.drawable.ovalemer,
        R.drawable.emeremer,
        R.drawable.bagemer,
        R.drawable.marcemer
    )
    var imageArrayAmethyst:IntArray = intArrayOf(
        R.drawable.roundamethyst,
        R.drawable.princamet,
        R.drawable.ovalamet,
        R.drawable.emeramet,
        R.drawable.bagamet,
        R.drawable.marcamet
    )
    var imageArrayAquamarine:IntArray = intArrayOf(
        R.drawable.roundaqua,
        R.drawable.princaqua,
        R.drawable.ovalaqua,
        R.drawable.emeraqua,
        R.drawable.bagaqua,
        R.drawable.marcaqua
    )


    val gemParameters = mutableListOf(
        GemParameters(nameGem = "Diamond",densityGem = "3.52", nameEnum = GemParameters.NameGemEnum.DIAMOND),
        GemParameters(nameGem = "Rubin",densityGem = "3.99", nameEnum = GemParameters.NameGemEnum.RUBIN),
        GemParameters(nameGem = "Emerald",densityGem = "2.71", nameEnum = GemParameters.NameGemEnum.EMERALD),
        GemParameters(nameGem = "Сitrine",densityGem = "2.65", nameEnum = GemParameters.NameGemEnum.CITRINE),
        GemParameters(nameGem = "Amethyst",densityGem = "2.65", nameEnum = GemParameters.NameGemEnum.AMETHYST),
        GemParameters(nameGem = "Aquamarine",densityGem = "2.69", nameEnum = GemParameters.NameGemEnum.AQUAMARINE)
    )
    var selectedGemParameter: GemParameters = gemParameters[0]

    private val gemParametersListener = object : AdapterView.OnItemSelectedListener {
        override fun onItemSelected(p0: AdapterView<*>?, view: View?, positionMat: Int, id: Long) {
            val differentDensity = gemParameters[positionMat]
            selectedGemParameter = differentDensity

            val positionCut = selectedCutParameters.form.toInt()

            if (selectedGemParameter.nameEnum == GemParameters.NameGemEnum.DIAMOND) {
                binding.imageView.setImageResource(imageArrayDiamond[positionCut])
            } else if (selectedGemParameter.nameEnum == GemParameters.NameGemEnum.RUBIN) {
                binding.imageView.setImageResource(imageArrayRubin[positionCut])
            } else if (selectedGemParameter.nameEnum == GemParameters.NameGemEnum.EMERALD) {
                binding.imageView.setImageResource(imageArrayEmerald[positionCut])
            } else if (selectedGemParameter.nameEnum == GemParameters.NameGemEnum.CITRINE) {
                binding.imageView.setImageResource(imageArrayCitrin[positionCut])
            } else if (selectedGemParameter.nameEnum == GemParameters.NameGemEnum.AMETHYST) {
                binding.imageView.setImageResource(imageArrayAmethyst[positionCut])
            } else if (selectedGemParameter.nameEnum == GemParameters.NameGemEnum.AQUAMARINE) {
                binding.imageView.setImageResource(imageArrayAquamarine[positionCut])
            }


        }



        override fun onNothingSelected(position: AdapterView<*>?) {

        }
    }

    val cutType = mutableListOf(
        CutType(name = "Round", form = "0", calculationCoefficient = "0.0018"),
        CutType(name = "Princess", form = "1", calculationCoefficient = "0.0023"),
        CutType(name = "Oval",form = "2", calculationCoefficient = "0.0018"),
        CutType(name = "Emerald",form = "3", calculationCoefficient = "0.00245"),
        CutType(name = "Baguette",form = "4", calculationCoefficient = "0.0029"),
        CutType(name = "Marquis",form = "5", calculationCoefficient = "0.0016")
    )
    var selectedCutParameters: CutType = cutType[0]

    private val cutTypeListener = object : AdapterView.OnItemSelectedListener {
        override fun onItemSelected(p0: AdapterView<*>?, view: View?, position: Int, id: Long) {
            val differentTypeCut = cutType[position]
            selectedCutParameters = differentTypeCut

            val positionCut = selectedCutParameters.form.toInt()

            if (selectedGemParameter.nameEnum == GemParameters.NameGemEnum.DIAMOND) {
                binding.imageView.setImageResource(imageArrayDiamond[positionCut])
            } else if (selectedGemParameter.nameEnum == GemParameters.NameGemEnum.RUBIN) {
                binding.imageView.setImageResource(imageArrayRubin[positionCut])
            } else if (selectedGemParameter.nameEnum == GemParameters.NameGemEnum.EMERALD) {
                binding.imageView.setImageResource(imageArrayEmerald[positionCut])
            } else if (selectedGemParameter.nameEnum == GemParameters.NameGemEnum.CITRINE) {
                binding.imageView.setImageResource(imageArrayCitrin[positionCut])
            } else if (selectedGemParameter.nameEnum == GemParameters.NameGemEnum.AMETHYST) {
                binding.imageView.setImageResource(imageArrayAmethyst[positionCut])
            } else if (selectedGemParameter.nameEnum == GemParameters.NameGemEnum.AQUAMARINE) {
                binding.imageView.setImageResource(imageArrayAquamarine[positionCut])
            }

        }

        override fun onNothingSelected(position: AdapterView<*>?) {

        }
    }



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCalculateCaratBinding.inflate(layoutInflater)
        setContentView(binding.root)


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
            this,
            android.R.layout.simple_list_item_1,
            android.R.id.text1,
            gemParameters
        )
        binding.densitySpinner.adapter = adapter

        binding.densitySpinner.onItemSelectedListener = gemParametersListener

    }

    private fun setupListParameters2() {
        adapterCut = ArrayAdapter(
            this,
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
        val sizePrincess = (lengthGem+widthGem)/2

        if (selectedCutParameters.name == "Oval") {
            sizeGem = sizePrincess * sizePrincess * depthGem
        } else {
            sizeGem = lengthGem * widthGem * depthGem
        }

        val gemDensity = sizeGem * densityGemCalculate
        val gemWeightResult = gemDensity * typeCutCalculate
        val gemWeightResultFloor = floor(gemWeightResult * 1000.0) /1000.0

        val gemWeightGramms = gemWeightResult * 0.2
        val gemWeightGrammsFloor = floor(gemWeightGramms * 1000.0) /1000.0

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
        val form: String,
        val calculationCoefficient: String
    ) {
        override fun toString(): String {
            return name
        }
        /*enum class CutForm {
            OVAL,
            PRINCESS,
            ROUND

        }*/
    }

}
