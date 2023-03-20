package com.example.weightofring.data.repositories

import android.app.Application
import androidx.lifecycle.LiveData
import com.example.weightofring.data.database.AppDatabase
import com.example.weightofring.data.database.gemresult.GemResult

class GemRepository(application: Application) {

    private val db = AppDatabase.getDatabase(application)

    fun getAllGemResult(): LiveData<List<GemResult>> {
        return db.gemResultDao().getAll()
    }

    suspend fun saveGameToDatabase(gemResult: GemResult) {
        db.gemResultDao().insertGemResult(gemResult)
    }

    suspend fun deleteGemResult(item: GemResult) {
        db.gemResultDao().deleteGemResult(item)
    }
}