package com.example.weightofring.domain.reposytories

import androidx.lifecycle.LiveData
import com.example.weightofring.data.database.gemresult.GemResult

interface GemRepository {

    fun getAllGemResult(): LiveData<List<GemResult>>

    suspend fun saveGameToDatabase(gemResult: GemResult)

    suspend fun deleteGemResult(item: GemResult)

}