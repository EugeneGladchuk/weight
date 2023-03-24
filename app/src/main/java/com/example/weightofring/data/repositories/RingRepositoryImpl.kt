package com.example.weightofring.data.repositories

import android.app.Application
import androidx.lifecycle.LiveData
import com.example.weightofring.data.database.AppDatabase
import com.example.weightofring.data.database.ringresult.RingResult
import com.example.weightofring.domain.reposytories.RingRepository

class RingRepositoryImpl(application: Application): RingRepository {

    private val db = AppDatabase.getDatabase(application)

    override fun getAllRingResult(): LiveData<List<RingResult>> {
        return db.ringResultDao().getAll()
    }

    override suspend fun saveRingToDatabase(ringResult: RingResult) {
        db.ringResultDao().insertRingResult(ringResult)
    }

    override suspend fun deleteRingResult(item: RingResult) {
        db.ringResultDao().deleteRingResult(item)
    }
}