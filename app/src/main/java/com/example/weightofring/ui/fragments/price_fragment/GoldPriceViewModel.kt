package com.example.weightofring.ui.fragments.price_fragment

import android.app.Application
import androidx.lifecycle.*
import com.example.weightofring.data.network.GoldPrice
import com.example.weightofring.data.repositories.PriceRepository
import kotlinx.coroutines.launch


class GoldPriceViewModel(application: Application): AndroidViewModel(application) {

    private val repository = PriceRepository()

    private val _goldPrice = MutableLiveData<GoldPrice>()
    val goldPrice: LiveData<GoldPrice> = _goldPrice

    fun buttonUpdateClicked() {
        viewModelScope.launch{

                _goldPrice.value = repository.getLatestPrice()

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
}




