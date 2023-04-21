package com.example.weightofring.ui.fragments.price_fragment

import android.app.Application
import androidx.lifecycle.*
import com.example.weightofring.di.factory.PriceRepositoryFactory
import com.example.weightofring.domain.model.GoldPriceForUi
import com.example.weightofring.domain.model.Lists
import com.example.weightofring.ui.fragments.price_fragment.model.CurrencyForSpinner
import com.example.weightofring.ui.fragments.price_fragment.model.MetalForSpinner
import com.example.weightofring.ui.fragments.price_fragment.model.UnitEnum
import kotlinx.coroutines.launch

class GoldPriceViewModel(application: Application) : AndroidViewModel(application) {

    private val repository = PriceRepositoryFactory.getPriceRepository(application)

    val goldPrice: LiveData<GoldPriceForUi?> = repository.getPriceFromDatabase()

    /*private val _listCurrency = MutableLiveData(
        mutableListOf(
            CurrencyForSpinner(currencyName = "US Dollar", rate = goldPrice.value?.currency?.get(0) ?: 0.0),
            CurrencyForSpinner(currencyName = "Russian Ruble", rate = goldPrice.value?.currency?.get(1) ?: 0.0),
            CurrencyForSpinner(currencyName = "Canadian Dollar", rate = goldPrice.value?.currency?.get(2) ?: 0.0),
            CurrencyForSpinner(currencyName = "Czech Koruna", rate = goldPrice.value?.currency?.get(3) ?: 0.0),
            CurrencyForSpinner(currencyName = "Euro", rate = goldPrice.value?.currency?.get(4) ?: 0.0),
            CurrencyForSpinner(currencyName = "Japanese Yen", rate = goldPrice.value?.currency?.get(5) ?: 0.0),
            CurrencyForSpinner(currencyName = "Turkish Lira", rate = goldPrice.value?.currency?.get(6) ?: 0.0),
            CurrencyForSpinner(currencyName = "Ukrainian Hryvnia", rate = goldPrice.value?.currency?.get(7) ?: 0.0),
            CurrencyForSpinner(currencyName = "UAE Dirham", rate = goldPrice.value?.currency?.get(8) ?: 0.0)
        )
    )*/

    private val _listCurrency = MutableLiveData<MutableList<CurrencyForSpinner>>(Lists.listCurrency)
    val listCurrency: LiveData<MutableList<CurrencyForSpinner>> get() = _listCurrency

    /*private val _listMetal = MutableLiveData(
        mutableListOf(
            MetalForSpinner(metalName = "Gold", metal = goldPrice.value?.metal?.get(0) ?: 0.0),
            MetalForSpinner(metalName = "Silver", metal = goldPrice.value?.metal?.get(1) ?: 0.0),
            MetalForSpinner(metalName = "Platinum", metal = goldPrice.value?.metal?.get(2) ?: 0.0),
            MetalForSpinner(metalName = "Palladium", metal = goldPrice.value?.metal?.get(3) ?: 0.0)
            )
    )*/
    private val _listMetal = MutableLiveData<MutableList<MetalForSpinner>>(Lists.listMetal)
    val listMetal: LiveData<MutableList<MetalForSpinner>> get() = _listMetal

    private val _allUnit = MutableLiveData(Lists.listForUnitSpinner)
    val allUnit: LiveData<List<UnitEnum>> get() = _allUnit

    private val _fromCurrencySpinnerPosition = MutableLiveData(0)
    val fromCurrencySpinnerPosition: LiveData<Int> get() = _fromCurrencySpinnerPosition

    private val _toCurrencySpinnerPosition = MutableLiveData(0)
    val toCurrencySpinnerPosition: LiveData<Int> get() = _toCurrencySpinnerPosition

    private val _metalSpinnerPosition = MutableLiveData(0)
    val metalSpinnerPosition: LiveData<Int> get() = _metalSpinnerPosition

    private val _unitSpinnerPosition = MutableLiveData(0)
    val unitSpinnerPosition: LiveData<Int> get() = _unitSpinnerPosition

    private val _priceCurrencySpinnerPosition = MutableLiveData(0)
    val priceCurrencySpinnerPosition: LiveData<Int> get() = _priceCurrencySpinnerPosition

    private val _textCurrencyFrom = MutableLiveData("")
    val textCurrencyFrom: LiveData<String> get() = _textCurrencyFrom

    private val _textCurrencyTo = MutableLiveData("")
    val textCurrencyTo: LiveData<String> get() = _textCurrencyTo

    private val _textUnit = MutableLiveData("")
    val textUnit: LiveData<String> get() = _textUnit

    private val _textCurrencyPrice = MutableLiveData("")
    val textCurrencyPrice: LiveData<String> get() = _textCurrencyPrice

    /*init {
        updateAllLists()
    }*/

    fun buttonUpdateClicked() {
        viewModelScope.launch {
            repository.getLatestPrice()

            /*viewModelScope.launch {
                    kotlin.runCatching {
                        _binDetailsState.value = BinDetailsState.Loading
                        val bin = binText.value
                        val binInfo = repository.loadBin(bin)
                        _binDetailsState.value = BinDetailsState.Success(binInfo)
                    }.onFailure {
                        _binDetailsState.value = BinDetailsState.Error("Ошибка получения информации о BIN")
                    }
                }*/
        }
    }

    /*fun updateAllLists() {

            _listCurrency.value = mutableListOf(
                CurrencyForSpinner(currencyName = "US Dollar", rate = goldPrice.value?.currency?.get(0) ?: 0.0),
                CurrencyForSpinner(currencyName = "Russian Ruble", rate = goldPrice.value?.currency?.get(1) ?: 0.0),
                CurrencyForSpinner(currencyName = "Canadian Dollar", rate = goldPrice.value?.currency?.get(2) ?: 0.0),
                CurrencyForSpinner(currencyName = "Czech Koruna", rate = goldPrice.value?.currency?.get(3) ?: 0.0),
                CurrencyForSpinner(currencyName = "Euro", rate = goldPrice.value?.currency?.get(4) ?: 0.0),
                CurrencyForSpinner(currencyName = "Japanese Yen", rate = goldPrice.value?.currency?.get(5) ?: 0.0),
                CurrencyForSpinner(currencyName = "Turkish Lira", rate = goldPrice.value?.currency?.get(6) ?: 0.0),
                CurrencyForSpinner(currencyName = "Ukrainian Hryvnia", rate = goldPrice.value?.currency?.get(7) ?: 0.0),
                CurrencyForSpinner(currencyName = "UAE Dirham", rate = goldPrice.value?.currency?.get(8) ?: 0.0)
            )

            _listMetal.value = mutableListOf(
                MetalForSpinner(metalName = "Gold", metal = goldPrice.value?.metal?.get(0) ?: 0.0),
                MetalForSpinner(metalName = "Silver", metal = goldPrice.value?.metal?.get(0) ?: 0.0),
                MetalForSpinner(metalName = "Platinum", metal = goldPrice.value?.metal?.get(0) ?: 0.0),
                MetalForSpinner(metalName = "Palladium", metal = goldPrice.value?.metal?.get(0) ?: 0.0)
            )
    }*/

    fun fromCurrencySpinnerChanged(fromCurrencySpinnerPosition: Int) {
        updateFromCurrencySpinner(fromCurrencySpinnerPosition)
    }

    fun toCurrencySpinnerChanged(toCurrencySpinnerPosition: Int) {
        updateToCurrencySpinner(toCurrencySpinnerPosition)
    }

    fun metalSpinnerChanged(metalSpinnerPosition: Int) {
        updateMetalSpinner(metalSpinnerPosition)
    }

    fun unitSpinnerChanged(unitSpinnerPosition: Int) {
        updateUnitSpinner(unitSpinnerPosition)
    }

    fun priceCurrencySpinnerChanged(priceCurrencySpinnerPosition: Int) {
        updatePriceCurrencySpinner(priceCurrencySpinnerPosition)
    }

    fun editTextFromChanged(newCurrencyFrom: String){
        updateCurrencyFrom(newCurrencyFrom)
    }

    fun editTextToChanged(newCurrencyTo: String){
        updateCurrencyTo(newCurrencyTo)
    }

    fun editTextUnitChanged(newUnit: String){
        updateUnit(newUnit)
    }

    fun editTextPriceChanged(newCurrencyForPrice: String){
        updateCurrencyPrice(newCurrencyForPrice)
    }

    private fun updateFromCurrencySpinner(fromCurrencySpinnerPosition: Int) {
        if (_fromCurrencySpinnerPosition.value != fromCurrencySpinnerPosition) {
            _fromCurrencySpinnerPosition.value = fromCurrencySpinnerPosition
        }
    }

    private fun updateToCurrencySpinner(toCurrencySpinnerPosition: Int) {
        if (_toCurrencySpinnerPosition.value != toCurrencySpinnerPosition) {
            _toCurrencySpinnerPosition.value = toCurrencySpinnerPosition
        }
    }

    private fun updateMetalSpinner(metalSpinnerPosition: Int) {
        if (_metalSpinnerPosition.value != metalSpinnerPosition) {
            _metalSpinnerPosition.value = metalSpinnerPosition
        }
    }

    private fun updateUnitSpinner(unitSpinnerPosition: Int) {
        if (_unitSpinnerPosition.value != unitSpinnerPosition) {
            _unitSpinnerPosition.value = unitSpinnerPosition
        }
    }

    private fun updatePriceCurrencySpinner(priceCurrencySpinnerPosition: Int) {
        if (_priceCurrencySpinnerPosition.value != priceCurrencySpinnerPosition) {
            _priceCurrencySpinnerPosition.value = priceCurrencySpinnerPosition
        }
    }

    private fun updateCurrencyFrom(currencyFrom: String) {
        if (_textCurrencyFrom.value != currencyFrom) {
            _textCurrencyFrom.value = currencyFrom
        }
    }

    private fun updateCurrencyTo(currencyTo: String) {
        if (_textCurrencyTo.value != currencyTo) {
            _textCurrencyTo.value = currencyTo
        }
    }

    private fun updateUnit(unit: String) {
        if (_textUnit.value != unit) {
            _textUnit.value = unit
        }
    }

    private fun updateCurrencyPrice(currencyForPrice: String) {
        if (_textCurrencyPrice.value != currencyForPrice) {
            _textCurrencyPrice.value = currencyForPrice
        }
    }
}




