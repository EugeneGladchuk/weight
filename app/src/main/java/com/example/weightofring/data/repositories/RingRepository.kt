package com.example.weightofring.data.repositories

import android.app.Application
import androidx.lifecycle.LiveData
import com.example.weightofring.data.database.AppDatabase
import com.example.weightofring.data.database.ringresult.RingResult

class RingRepository(application: Application) {

    private val db = AppDatabase.getDatabase(application)

    fun getAllRingResult(): LiveData<List<RingResult>> {
        return db.ringResultDao().getAll()
    }

    suspend fun saveRingToDatabase(ringResult: RingResult) {
        db.ringResultDao().insertRingResult(ringResult)
    }

    suspend fun deleteRingResult(item: RingResult) {
        db.ringResultDao().deleteRingResult(item)
    }
}