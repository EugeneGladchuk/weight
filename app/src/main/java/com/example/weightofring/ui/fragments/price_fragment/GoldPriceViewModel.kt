package com.example.weightofring.ui.fragments.price_fragment

import android.app.Application
import androidx.lifecycle.*
import com.example.weightofring.di.factory.PriceRepositoryFactory
import com.example.weightofring.domain.model.GoldPriceForUi
import com.example.weightofring.domain.model.Lists
import com.example.weightofring.domain.use_case.*
import com.example.weightofring.ui.fragments.price_fragment.model.CurrencyForSpinner
import com.example.weightofring.ui.fragments.price_fragment.model.GoldPriceState
import com.example.weightofring.ui.fragments.price_fragment.model.MetalForSpinner
import com.example.weightofring.ui.fragments.price_fragment.model.UnitEnum
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import java.time.Instant
import java.time.LocalDateTime
import java.time.ZoneId
import java.time.format.DateTimeFormatter

class GoldPriceViewModel(application: Application) : AndroidViewModel(application) {

    private val repository = PriceRepositoryFactory.getPriceRepository(application)

    private val calculateTimeUntilNextUpdateUseCase = CalculateTimeUntilNextUpdateUseCase()
    private val calculateExchangeRateUseCase = CalculateExchangeRateUseCase()
    private val calculateExchangeRateOtherSideUseCase = CalculateExchangeRateOtherSideUseCase()
    private val calculateMetalPriceUseCase = CalculateMetalPriceUseCase()
    private val calculateMetalPriceOtherSideUseCase = CalculateMetalPriceOtherSideUseCase()

    val goldPrice: LiveData<GoldPriceForUi?> = repository.getPriceFromDatabase()

    private val _goldPriceState = MutableLiveData<GoldPriceState>(GoldPriceState.NoData)
    val goldPriceState: LiveData<GoldPriceState> get() = _goldPriceState

    private val _listCurrency = MutableLiveData<MutableList<CurrencyForSpinner>>(Lists.listCurrency)
    val listCurrency: LiveData<MutableList<CurrencyForSpinner>> get() = _listCurrency

    private val _listMetal = MutableLiveData<MutableList<MetalForSpinner>>(Lists.listMetal)
    val listMetal: LiveData<MutableList<MetalForSpinner>> get() = _listMetal

    private val _allUnit = MutableLiveData(Lists.listForUnitSpinner)
    val allUnit: LiveData<List<UnitEnum>> get() = _allUnit

    private val _lastUpdateDate = MutableLiveData<String>("")
    val lastUpdateDate: LiveData<String> get() = _lastUpdateDate

    private val _fromCurrencySpinnerPosition = MutableLiveData(0)
    val fromCurrencySpinnerPosition: LiveData<Int> get() = _fromCurrencySpinnerPosition

    private val _toCurrencySpinnerPosition = MutableLiveData(1)
    val toCurrencySpinnerPosition: LiveData<Int> get() = _toCurrencySpinnerPosition

    private val _metalSpinnerPosition = MutableLiveData(0)
    val metalSpinnerPosition: LiveData<Int> get() = _metalSpinnerPosition

    private val _unitSpinnerPosition = MutableLiveData(0)
    val unitSpinnerPosition: LiveData<Int> get() = _unitSpinnerPosition

    private val _priceCurrencySpinnerPosition = MutableLiveData(0)
    val priceCurrencySpinnerPosition: LiveData<Int> get() = _priceCurrencySpinnerPosition

    private val _textCurrencyFrom = MutableLiveData("1")
    val textCurrencyFrom: LiveData<String> get() = _textCurrencyFrom

    private val _textCurrencyTo = MutableLiveData("")
    val textCurrencyTo: LiveData<String> get() = _textCurrencyTo

    private val _textUnit = MutableLiveData("1")
    val textUnit: LiveData<String> get() = _textUnit

    private val _textCurrencyPrice = MutableLiveData("")
    val textCurrencyPrice: LiveData<String> get() = _textCurrencyPrice


    fun buttonUpdateClicked(): Boolean {
        if (goldPrice.value == null) {
            updateData()
        } else {
            if (calculateTimeUntilNextUpdateUseCase.calculateTime(goldPrice.value!!.updateTime)) {
                updateData()
            }
        }
        return if (goldPrice.value != null) {
            calculateTimeUntilNextUpdateUseCase.calculateTime(goldPrice.value!!.updateTime)
        } else {
            false
        }
    }

    fun checkGoldPriceForNull(): Boolean {
        return goldPrice.value == null
    }

    private fun updateData() {
        viewModelScope.launch {
            kotlin.runCatching {
                _goldPriceState.value = GoldPriceState.Loading
                repository.getLatestPrice()
                _goldPriceState.value = GoldPriceState.Success
            }.onFailure {
                _goldPriceState.value = GoldPriceState.Error
                delay(100)
                if (goldPrice.value == null) {
                    _goldPriceState.value = GoldPriceState.NoData
                } else {
                    _goldPriceState.value = GoldPriceState.Success
                }
            }
        }
    }

    private fun convertLastUpdateDate(time: Long): String {
        val date = LocalDateTime.ofInstant(
            Instant.ofEpochSecond(time),
            ZoneId.systemDefault()
        )
        val formattedDate = DateTimeFormatter.ofPattern("dd MMMM yyyy HH:mm").format(date)
        return formattedDate.toString()
    }

    fun getDateForDialog(): String {
        val availableDate = calculateTimeUntilNextUpdateUseCase.calculateAvailableDate(goldPrice.value!!.updateTime)
        return convertLastUpdateDate(availableDate)
    }

    fun goldPriceChanged() {
        _goldPriceState.value = GoldPriceState.Success
        _lastUpdateDate.value = convertLastUpdateDate(goldPrice.value!!.updateTime)
        calculateExchange()
        calculateMetalPrice()
    }

    private fun calculateExchange() {
        if (_textCurrencyFrom.value.isNullOrEmpty() || _textCurrencyFrom.value == ".") {
            _textCurrencyTo.value = ""
        } else {
            if (goldPrice.value != null) {
                val fromPosition = _fromCurrencySpinnerPosition.value!!
                val toPosition = _toCurrencySpinnerPosition.value!!
                val amount = _textCurrencyFrom.value ?: ""
                val goldPrice = goldPrice.value!!

                _textCurrencyTo.value = calculateExchangeRateUseCase.calculateExchange(
                    fromPosition,
                    toPosition,
                    amount.toDouble(),
                    goldPrice
                )
            }
        }
    }

    private fun calculateExchangeOtherSide() {
        if (_textCurrencyTo.value.isNullOrEmpty() || _textCurrencyTo.value == ".") {
            _textCurrencyFrom.value = ""
        } else {
            if (goldPrice.value != null) {
                val fromPosition = _fromCurrencySpinnerPosition.value!!
                val toPosition = _toCurrencySpinnerPosition.value!!
                val amount = _textCurrencyTo.value ?: ""
                val goldPrice = goldPrice.value!!

                _textCurrencyFrom.value = calculateExchangeRateOtherSideUseCase.calculateExchange(
                    fromPosition,
                    toPosition,
                    amount.toDouble(),
                    goldPrice
                )
            }
        }
    }

    private fun calculateMetalPrice() {
        if (_textUnit.value.isNullOrEmpty() || _textUnit.value == ".") {
            _textCurrencyPrice.value = ""
        } else {
            if (goldPrice.value != null) {
                val metalPosition = _metalSpinnerPosition.value!!
                val unitPosition = _unitSpinnerPosition.value!!
                val unitsList = _allUnit.value!!
                val currencyPosition = _priceCurrencySpinnerPosition.value!!
                val amountMetal = _textUnit.value ?: ""
                val goldPrice = goldPrice.value!!

                _textCurrencyPrice.value = calculateMetalPriceUseCase.calculateMetalPrice(
                    metalPosition,
                    unitPosition,
                    unitsList,
                    currencyPosition,
                    amountMetal.toDouble(),
                    goldPrice
                )
            }
        }
    }

    private fun calculateMetalPriceOtherSide() {
        if (_textCurrencyPrice.value.isNullOrEmpty() || _textCurrencyPrice.value == ".") {
            _textUnit.value = ""
        } else {
            if (goldPrice.value != null) {
                val metalPosition = _metalSpinnerPosition.value!!
                val unitPosition = _unitSpinnerPosition.value!!
                val unitsList = _allUnit.value!!
                val currencyPosition = _priceCurrencySpinnerPosition.value!!
                val amountMoney = _textCurrencyPrice.value ?: ""
                val goldPrice = goldPrice.value!!

                _textUnit.value = calculateMetalPriceOtherSideUseCase.calculateMetalPrice(
                    metalPosition,
                    unitPosition,
                    unitsList,
                    currencyPosition,
                    amountMoney.toDouble(),
                    goldPrice
                )
            }
        }
    }

    fun fromCurrencySpinnerChanged(fromCurrencySpinnerPosition: Int) {
        updateFromCurrencySpinner(fromCurrencySpinnerPosition)
        calculateExchange()
    }

    fun toCurrencySpinnerChanged(toCurrencySpinnerPosition: Int) {
        updateToCurrencySpinner(toCurrencySpinnerPosition)
        calculateExchange()
    }

    fun metalSpinnerChanged(metalSpinnerPosition: Int) {
        updateMetalSpinner(metalSpinnerPosition)
        calculateMetalPrice()
    }

    fun unitSpinnerChanged(unitSpinnerPosition: Int) {
        updateUnitSpinner(unitSpinnerPosition)
        calculateMetalPrice()
    }

    fun priceCurrencySpinnerChanged(priceCurrencySpinnerPosition: Int) {
        updatePriceCurrencySpinner(priceCurrencySpinnerPosition)
        calculateMetalPrice()
    }

    fun editTextFromChanged(newCurrencyFrom: String) {
        updateCurrencyFrom(newCurrencyFrom)
        calculateExchange()
    }

    fun editTextToChanged(newCurrencyTo: String) {
        updateCurrencyTo(newCurrencyTo)
        calculateExchangeOtherSide()
    }


    fun editTextUnitChanged(newUnit: String) {
        updateUnit(newUnit)
        calculateMetalPrice()
    }

    fun editTextPriceChanged(newPrice: String) {
        updatePrice(newPrice)
        calculateMetalPriceOtherSide()
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

    private fun updatePrice(price: String) {
        if (_textCurrencyPrice.value != price) {
            _textCurrencyPrice.value = price
        }
    }
}




