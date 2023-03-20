package com.example.weightofring.data.database.gemresult

import androidx.lifecycle.LiveData
import androidx.room.*


@Dao
interface GemResultDao {

    @Query("SELECT * FROM gemresult ORDER BY id DESC")
    fun getAll(): LiveData<List<GemResult>>

    @Insert
    suspend fun insertGemResult(gemResult: GemResult)

    @Delete
    suspend fun deleteGemResult(gemResult: GemResult)

    @Update
    suspend fun updateGemResult(gemResult: GemResult)
}