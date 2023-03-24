package com.example.weightofring.domain.reposytories

import androidx.lifecycle.LiveData
import com.example.weightofring.data.database.ringresult.RingResult

interface RingRepository {

    fun getAllRingResult(): LiveData<List<RingResult>>

    suspend fun saveRingToDatabase(ringResult: RingResult)

    suspend fun deleteRingResult(item: RingResult)

}