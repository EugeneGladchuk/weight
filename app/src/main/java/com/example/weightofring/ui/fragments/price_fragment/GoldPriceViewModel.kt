package com.example.weightofring.ui.fragments.price_fragment

import android.app.Application
import androidx.lifecycle.*
import com.example.weightofring.di.factory.PriceRepositoryFactory
import com.example.weightofring.domain.model.GoldPriceForUi
import kotlinx.coroutines.launch


class GoldPriceViewModel(application: Application): AndroidViewModel(application) {

    private val repository = PriceRepositoryFactory.getPriceRepository(application)

    private val _goldPrice = MutableLiveData<GoldPriceForUi>()
    val goldPrice: LiveData<GoldPriceForUi> = _goldPrice

    init {
        viewModelScope.launch{
            _goldPrice.value = repository.getPriceFromDatabase()
        }
    }

    fun buttonUpdateClicked() {
        viewModelScope.launch{
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
}




