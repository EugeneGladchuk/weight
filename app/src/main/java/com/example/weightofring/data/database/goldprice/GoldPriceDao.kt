package com.example.weightofring.data.database.goldprice

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.room.*

@Dao
interface GoldPriceDao {

    @Query("SELECT * FROM goldpriceentity ORDER BY id DESC")
    fun getLatestGoldPrice(): LiveData<GoldPriceEntity>

    @Insert
    suspend fun insertLatestGoldPrice(goldPriceEntity: GoldPriceEntity)

    @Delete
    suspend fun deleteLatestGoldPrice(goldPriceEntity: GoldPriceEntity)

    @Update
    suspend fun updateLatestGoldPrice(goldPriceEntity: GoldPriceEntity)

}