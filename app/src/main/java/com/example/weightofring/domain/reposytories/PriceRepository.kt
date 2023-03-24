package com.example.weightofring.domain.reposytories

import com.example.weightofring.data.network.GoldPriceApi
import com.example.weightofring.domain.model.GoldPriceForUi

interface PriceRepository {
    suspend fun getLatestPrice(): GoldPriceApi
    suspend fun getPriceFromDatabase(): GoldPriceForUi
}