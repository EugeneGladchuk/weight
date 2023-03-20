package com.example.weightofring.domain

import androidx.lifecycle.LiveData
import com.example.weightofring.data.database.gemresult.GemResult

interface Repository {

    fun getAll(): LiveData<List<GemResult>>
    suspend fun saveToDatabase(gemResult: GemResult)
    suspend fun deleteGemResult(item: GemResult)

    /*suspend fun insertResult() = db.ringResultDao().insertRingResult()*/

    //TODO почитать на сайте гугла по андроиду Architecture (Domain layer, Data layer, UI layer)
}