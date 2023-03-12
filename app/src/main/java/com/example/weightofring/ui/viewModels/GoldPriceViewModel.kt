package com.example.weightofring.ui.viewModels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.weightofring.data.network.GoldPrice
import com.example.weightofring.data.network.PriceApi
import com.example.weightofring.utils.Constants.Companion.API_KEY
import kotlinx.coroutines.launch


class GoldPriceViewModel: ViewModel() {

    val repository = PriceApi.retrofitService

    private val _goldPrice = MutableLiveData<GoldPrice>()
    val goldPrice: LiveData<GoldPrice> = _goldPrice

    fun buttonUpdateClicked() {
        viewModelScope.launch{

                _goldPrice.value = repository.getLatestPrice(API_KEY)



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