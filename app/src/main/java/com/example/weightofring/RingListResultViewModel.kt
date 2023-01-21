package com.example.weightofring

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.example.weightofring.database.AppDatabase
import com.example.weightofring.database.ringresult.RingResult
import kotlinx.coroutines.launch

class RingListResultViewModel(application: Application) : AndroidViewModel(application) {

    private val db = AppDatabase.getDatabase(application)

    val myDataList: LiveData<List<RingResult>> = db.ringResultDao().getAll()

    fun deleteButtonClick(item: RingResult) {
        viewModelScope.launch {
            db.ringResultDao().deleteRingResult(item)
        }
    }
}