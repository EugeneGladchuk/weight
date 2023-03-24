package com.example.weightofring.data.database.goldprice

import androidx.room.*

@Dao
interface GoldPriceDao {

    @Query("SELECT * FROM goldpriceentity")
    suspend fun getLatestGoldPrice(): GoldPriceEntity

    @Insert
    suspend fun insertLatestGoldPrice(goldPriceEntity: GoldPriceEntity)

    @Delete
    suspend fun deleteLatestGoldPrice(goldPriceEntity: GoldPriceEntity)

    @Update
    suspend fun updateLatestGoldPrice(goldPriceEntity: GoldPriceEntity)

}