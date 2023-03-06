package com.example.weightofring.ui.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import androidx.core.widget.doOnTextChanged
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.weightofring.ui.viewModels.CalculateGemViewModel
import com.example.weightofring.databinding.FragmentCalculateGemBinding
import com.example.weightofring.domain.model.CutType
import com.example.weightofring.domain.model.GemParameters

class CalculateGemFragment : Fragment() {

    lateinit var viewModel: CalculateGemViewModel

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

        viewModel = ViewModelProvider(requireActivity())[CalculateGemViewModel::class.java]

        binding.lengthEditText.doOnTextChanged { text, start, before, count ->
            val newValue = if (text.isNullOrBlank()) " " else text.toString()
            viewModel.lengthTextChanged(newValue)
        }

        binding.widthEditText.doOnTextChanged { text, start, before, count ->
            val newValue = if (text.isNullOrBlank()) " " else text.toString()
            viewModel.widthTextChanged(newValue)
        }

        binding.depthEditText.doOnTextChanged { text, start, before, count ->
            val newValue = if (text.isNullOrBlank()) " " else text.toString()
            viewModel.depthTextChanged(newValue)
        }

        viewModel.gemImage.observe(viewLifecycleOwner) {
            binding.imageView.setImageResource(it)
        }

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

        viewModel.lengthGem.observe(viewLifecycleOwner) {
            if (it.text != binding.lengthEditText.text.toString()) {
                binding.lengthEditText.setText(it.text)
            }
            if (it.error) {
                binding.lengthEditText.error = "error"
            } else {
                binding.lengthEditText.error = null
            }
        }

        viewModel.widthGem.observe(viewLifecycleOwner) {
            if (it.text != binding.widthEditText.text.toString()) {
                binding.widthEditText.setText(it.text)
            }
            if (it.error) {
                binding.widthEditText.error = "error"
            } else {
                binding.widthEditText.error = null
            }
        }

        viewModel.depthGem.observe(viewLifecycleOwner) {
            if (it.text != binding.depthEditText.text.toString()) {
                binding.depthEditText.setText(it.text)
            }
            if (it.error) {
                binding.depthEditText.error = "error"
            } else {
                binding.depthEditText.error = null
            }
        }

        viewModel.resultCarat.observe(viewLifecycleOwner) {
            if (it.toString() != binding.textViewResult.text) {
                binding.textViewResult.text = it.toString()
            }
        }

        viewModel.resultGram.observe(viewLifecycleOwner) {
            if (it.toString() != binding.resultGemGrammTV.text) {
                binding.resultGemGrammTV.text = it.toString()
            }
        }

        binding.buttonResultGem.setOnClickListener {
            viewModel.calculate()
        }
    }

    private fun setupListGemParameters(list: List<GemParameters>) {
        adapter = ArrayAdapter(
            requireContext(),
            android.R.layout.simple_list_item_activated_1,
            android.R.id.text1,
            list
        )
        binding.gemSpinner.adapter = adapter
        binding.gemSpinner.onItemSelectedListener = gemTypeListener
    }

    private fun setupListCutParameters(list: List<CutType>) {
        adapterCut = ArrayAdapter(
            requireContext(),
            android.R.layout.simple_list_item_activated_1,
            android.R.id.text1,
            list
        )
        binding.cutSpinner.adapter = adapterCut
        binding.cutSpinner.onItemSelectedListener = cutTypeListener
    }

    companion object {

        const val GEM = "CalculateGemFragment"

        @JvmStatic
        fun newInstance() = CalculateGemFragment()
    }
}