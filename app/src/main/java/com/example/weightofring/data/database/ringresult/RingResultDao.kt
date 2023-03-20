package com.example.weightofring.data.database.ringresult

import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
interface RingResultDao {

    @Query("SELECT * FROM ringresult ORDER BY id DESC")
    fun getAll(): LiveData<List<RingResult>>

    @Insert
    suspend fun insertRingResult(ringResult: RingResult)

    @Delete
    suspend fun deleteRingResult(ringResult: RingResult)

    @Update
    suspend fun updateRingResult(ringResult: RingResult)
}