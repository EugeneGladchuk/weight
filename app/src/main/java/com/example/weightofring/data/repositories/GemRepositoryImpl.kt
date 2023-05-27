package com.example.weightofring.data.repositories

import android.app.Application
import androidx.lifecycle.LiveData
import com.example.weightofring.data.database.AppDatabase
import com.example.weightofring.data.database.gemresult.GemResult
import com.example.weightofring.domain.reposytories.GemRepository

class GemRepositoryImpl(application: Application): GemRepository {

    private val db = AppDatabase.getDatabase(application)

    override fun getAllGemResult(): LiveData<List<GemResult>> {
        return db.gemResultDao().getAll()
    }

    override suspend fun saveGameToDatabase(gemResult: GemResult) {
        db.gemResultDao().insertGemResult(gemResult)
    }

    override suspend fun deleteGemResult(item: GemResult) {
        db.gemResultDao().deleteGemResult(item)
    }
}