package com.example.weightofring.domain.reposytories

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.weightofring.data.network.GoldPriceApi
import com.example.weightofring.domain.model.GoldPriceForUi

interface PriceRepository {
    suspend fun getLatestPrice(): GoldPriceApi
    fun getPriceFromDatabase(): LiveData<GoldPriceForUi?>
}