package com.example.weightofring.data.repositories

import com.example.weightofring.data.network.GoldPrice
import com.example.weightofring.data.network.PriceApi
import com.example.weightofring.utils.Constants.Companion.API_KEY

class PriceRepository {

    private val apiService = PriceApi.retrofitService

    suspend fun getLatestPrice(): GoldPrice {
        return apiService.getLatestPrice(API_KEY)
    }
}