package com.example.weightofring.data.database.gemresult

import androidx.lifecycle.LiveData
import androidx.room.*


@Dao
interface GemResultDao {

    @Query("SELECT * FROM gemresult ORDER BY id DESC")
    fun getAll(): LiveData<List<GemResult>>

    @Insert
    suspend fun insertGemResult(ringResult: GemResult)

    @Delete
    suspend fun deleteGemResult(ringResult: GemResult)

    @Update
    suspend fun updateGemResult(ringResult: GemResult)
}