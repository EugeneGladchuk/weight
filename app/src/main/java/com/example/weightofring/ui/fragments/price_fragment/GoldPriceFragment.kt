package com.example.weightofring.ui.fragments.price_fragment

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.core.view.isVisible
import androidx.lifecycle.ViewModelProvider
import com.example.weightofring.databinding.FragmentGoldPriceBinding
import com.example.weightofring.ui.fragments.price_fragment.model.CurrencyForSpinner
import com.example.weightofring.ui.fragments.price_fragment.model.GoldPriceState
import com.example.weightofring.ui.fragments.price_fragment.model.MetalForSpinner
import com.example.weightofring.ui.fragments.price_fragment.model.UnitEnum

class GoldPriceFragment : Fragment() {

    lateinit var viewModel: GoldPriceViewModel

    private lateinit var binding: FragmentGoldPriceBinding

    private lateinit var adapterCurrency: ArrayAdapter<CurrencyForSpinner>
    private lateinit var adapterMetal: ArrayAdapter<MetalForSpinner>
    private lateinit var adapterUnit: ArrayAdapter<UnitEnum>

    private val editTextFromListener = object: TextWatcher {
        override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
            //DO NOTHING
        }
        override fun onTextChanged(text: CharSequence?, p1: Int, p2: Int, p3: Int) {
            val newValue = if (text.isNullOrBlank()) "" else text.toString()
            viewModel.editTextFromChanged(newValue)
        }
        override fun afterTextChanged(p0: Editable?) {
            //DO NOTHING
        }
    }

    private val editTextToListener = object: TextWatcher {
        override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
            //DO NOTHING
        }
        override fun onTextChanged(text: CharSequence?, p1: Int, p2: Int, p3: Int) {
            val newValue = if (text.isNullOrBlank()) "" else text.toString()
            viewModel.editTextToChanged(newValue)
        }
        override fun afterTextChanged(p0: Editable?) {
            //DO NOTHING
        }
    }

    private val editTextUnitListener = object: TextWatcher {
        override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
            //DO NOTHING
        }
        override fun onTextChanged(text: CharSequence?, p1: Int, p2: Int, p3: Int) {
            val newValue = if (text.isNullOrBlank()) "" else text.toString()
            viewModel.editTextUnitChanged(newValue)
        }
        override fun afterTextChanged(p0: Editable?) {
            //DO NOTHING
        }
    }

    private val editTextPriceListener = object: TextWatcher {
        override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
            //DO NOTHING
        }

        override fun onTextChanged(text: CharSequence?, p1: Int, p2: Int, p3: Int) {
            val newValue = if (text.isNullOrBlank()) "" else text.toString()
            viewModel.editTextPriceChanged(newValue)
        }

        override fun afterTextChanged(p0: Editable?) {
            //DO NOTHING
        }
    }

    private val fromCurrencySpinnerListener = object : AdapterView.OnItemSelectedListener {
        override fun onItemSelected(p0: AdapterView<*>?, view: View?, position: Int, id: Long) {
            viewModel.fromCurrencySpinnerChanged(position)
        }

        override fun onNothingSelected(position: AdapterView<*>?) {
        }
    }

    private val toCurrencySpinnerListener = object : AdapterView.OnItemSelectedListener {
        override fun onItemSelected(p0: AdapterView<*>?, view: View?, position: Int, id: Long) {
            viewModel.toCurrencySpinnerChanged(position)
        }

        override fun onNothingSelected(position: AdapterView<*>?) {
        }
    }

    private val metalSpinnerListener = object : AdapterView.OnItemSelectedListener {
        override fun onItemSelected(p0: AdapterView<*>?, view: View?, position: Int, id: Long) {
            viewModel.metalSpinnerChanged(position)
        }

        override fun onNothingSelected(position: AdapterView<*>?) {
        }
    }

    private val unitSpinnerListener = object : AdapterView.OnItemSelectedListener {
        override fun onItemSelected(p0: AdapterView<*>?, view: View?, position: Int, id: Long) {
            viewModel.unitSpinnerChanged(position)
        }

        override fun onNothingSelected(position: AdapterView<*>?) {
        }
    }

    private val priceCurrencySpinnerListener = object : AdapterView.OnItemSelectedListener {
        override fun onItemSelected(p0: AdapterView<*>?, view: View?, position: Int, id: Long) {
            viewModel.priceCurrencySpinnerChanged(position)
        }

        override fun onNothingSelected(position: AdapterView<*>?) {
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentGoldPriceBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        viewModel = ViewModelProvider(requireActivity())[GoldPriceViewModel::class.java]

        binding.editTextFrom.addTextChangedListener(editTextFromListener)
        binding.editTextTo.addTextChangedListener(editTextToListener)

        binding.editTextUnit.addTextChangedListener(editTextUnitListener)
        binding.editTextPrice.addTextChangedListener(editTextPriceListener)

        binding.buttonUpdate.setOnClickListener {

            if (!viewModel.buttonUpdateClicked()) {
                if (!viewModel.checkGoldPriceForNull()) {
                    showNegativeDialog()
                }
            }
        }

        viewModel.listCurrency.observe(viewLifecycleOwner) {
            setupFromCurrencyList(it)
        }

        viewModel.listMetal.observe(viewLifecycleOwner) {
            setupMetalList(it)
        }

        viewModel.allUnit.observe(viewLifecycleOwner) {
            setupUnitList(it)
        }

        viewModel.lastUpdateDate.observe(viewLifecycleOwner) {
            if (it != binding.textViewDate.text) {
                binding.textViewDate.text = it
            }
        }

        viewModel.textCurrencyFrom.observe(viewLifecycleOwner) {
            if (it != binding.editTextFrom.text.toString()) {
                binding.editTextFrom.removeTextChangedListener(editTextFromListener)
                binding.editTextFrom.setText(it)
                binding.editTextFrom.addTextChangedListener(editTextFromListener)
            }
        }

        viewModel.textCurrencyTo.observe(viewLifecycleOwner) {
            if (it != binding.editTextTo.text.toString()){
                binding.editTextTo.removeTextChangedListener(editTextToListener)
                binding.editTextTo.setText(it)
                binding.editTextTo.addTextChangedListener(editTextToListener)
            }
        }

        viewModel.textUnit.observe(viewLifecycleOwner) {
            if (it != binding.editTextUnit.text.toString()) {
                binding.editTextUnit.removeTextChangedListener(editTextUnitListener)
                binding.editTextUnit.setText(it)
                binding.editTextUnit.addTextChangedListener(editTextUnitListener)
            }
        }

        viewModel.textCurrencyPrice.observe(viewLifecycleOwner) {
            if (it != binding.editTextPrice.text.toString()){
                binding.editTextPrice.removeTextChangedListener(editTextPriceListener)
                binding.editTextPrice.setText(it)
                binding.editTextPrice.addTextChangedListener(editTextPriceListener)
            }
        }

        viewModel.fromCurrencySpinnerPosition.observe(viewLifecycleOwner) {
            if (it != binding.usdFromSpinner.selectedItemPosition) {
                binding.usdFromSpinner.setSelection(it)
            }
        }

        viewModel.toCurrencySpinnerPosition.observe(viewLifecycleOwner) {
            if (it != binding.usdToSpinner.selectedItemPosition) {
                binding.usdToSpinner.setSelection(it)
            }
        }

        viewModel.metalSpinnerPosition.observe(viewLifecycleOwner) {
            if (it != binding.metalSpinner.selectedItemPosition) {
                binding.metalSpinner.setSelection(it)
            }
        }

        viewModel.unitSpinnerPosition.observe(viewLifecycleOwner) {
            if (it != binding.unitSpinner.selectedItemPosition) {
                binding.unitSpinner.setSelection(it)
            }
        }

        viewModel.priceCurrencySpinnerPosition.observe(viewLifecycleOwner) {
            if (it != binding.priceSpinner.selectedItemPosition) {
                binding.priceSpinner.setSelection(it)
            }
        }

        viewModel.goldPrice.observe(viewLifecycleOwner) {
            if (it != null) {
                viewModel.goldPriceChanged()
            }
        }

        viewModel.goldPriceState.observe(viewLifecycleOwner) { state ->
            when (state) {
                is GoldPriceState.NoData -> {
                    binding.textViewDate.text = "НЕТ ДАННЫХ"
                    binding.priceProgress.isVisible = false
                    binding.shield1.isVisible = true
                    binding.shield2.isVisible = true
                }
                is GoldPriceState.Loading -> {
                    binding.priceProgress.isVisible = true
                }
                is GoldPriceState.Success -> {
                    binding.priceProgress.isVisible = false
                    binding.shield1.isVisible = false
                    binding.shield2.isVisible = false
                }
                is GoldPriceState.Error -> {
                    binding.priceProgress.isVisible = false
                    showFailedDialog()
                }
            }
        }
    }

    private fun setupFromCurrencyList(list: MutableList<CurrencyForSpinner>) {
        adapterCurrency = ArrayAdapter(
            requireContext(),
            android.R.layout.simple_list_item_activated_1,
            android.R.id.text1,
            list
        )
        binding.usdToSpinner.adapter = adapterCurrency
        binding.usdToSpinner.onItemSelectedListener = toCurrencySpinnerListener
        binding.usdFromSpinner.adapter = adapterCurrency
        binding.usdFromSpinner.onItemSelectedListener = fromCurrencySpinnerListener
        binding.priceSpinner.adapter = adapterCurrency
        binding.priceSpinner.onItemSelectedListener = priceCurrencySpinnerListener
    }

    private fun setupMetalList(list: MutableList<MetalForSpinner>) {
        adapterMetal = ArrayAdapter(
            requireContext(),
            android.R.layout.simple_list_item_activated_1,
            android.R.id.text1,
            list
        )
        binding.metalSpinner.adapter = adapterMetal
        binding.metalSpinner.onItemSelectedListener = metalSpinnerListener
    }

    private fun setupUnitList(list: List<UnitEnum>) {
        adapterUnit = ArrayAdapter(
            requireContext(),
            android.R.layout.simple_list_item_activated_1,
            android.R.id.text1,
            list
        )
        binding.unitSpinner.adapter = adapterUnit
        binding.unitSpinner.onItemSelectedListener = unitSpinnerListener
    }

    private fun showNegativeDialog() {
        val availableTime = viewModel.getDateForDialog()
        val builder = AlertDialog.Builder(requireContext())
        builder.setMessage("Новые данные будут доступны\n$availableTime")
        builder.show()
    }

    private fun showFailedDialog() {
        val builder = AlertDialog.Builder(requireContext())
        builder.setTitle("Не удалось получить данные")
        builder.setMessage("Проверьте интернет подключение")
        builder.show()
    }


    companion object {

        const val PRICE = "GoldPriceFragment"

        fun newInstance() = GoldPriceFragment()
    }
}