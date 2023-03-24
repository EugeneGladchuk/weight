package com.example.weightofring.data.repositories

import android.app.Application
import com.example.weightofring.data.database.AppDatabase
import com.example.weightofring.data.database.goldprice.GoldPriceEntity
import com.example.weightofring.data.network.GoldPriceApi
import com.example.weightofring.data.network.PriceApi
import com.example.weightofring.domain.model.GoldPriceForUi
import com.example.weightofring.domain.reposytories.PriceRepository
import com.example.weightofring.utils.Constants.Companion.API_KEY

class PriceRepositoryImpl(application: Application): PriceRepository {

    private val db = AppDatabase.getDatabase(application)

    private val apiService = PriceApi.retrofitService

    override suspend fun getLatestPrice(): GoldPriceApi {
        val goldPriceApi = apiService.getLatestPrice(API_KEY)
        saveLatestPrice(goldPriceApi)
        return goldPriceApi
    }

    override suspend fun getPriceFromDatabase(): GoldPriceForUi {
        val goldPriceForUi = db.goldPriceDao().getLatestGoldPrice()
        return GoldPriceEntity.mapToGoldPriceForUi(goldPriceForUi)
    }


    private suspend fun saveLatestPrice(goldPriceApi: GoldPriceApi) {
        db.goldPriceDao().insertLatestGoldPrice(GoldPriceEntity.mapFromGoldPrice(goldPriceApi))
    }
}

