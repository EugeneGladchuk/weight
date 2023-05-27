package com.example.weightofring.data.repositories

import android.app.Application
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.map
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

    override fun getPriceFromDatabase(): LiveData<GoldPriceForUi?> {
        return db.goldPriceDao().getLatestGoldPrice().map {
            GoldPriceEntity.mapToGoldPriceForUi(it)
        }
    }


    private suspend fun saveLatestPrice(goldPriceApi: GoldPriceApi) {
        db.goldPriceDao().insertLatestGoldPrice(GoldPriceEntity.mapFromGoldPrice(goldPriceApi))
    }
}

