package com.example.weightofring.data.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.weightofring.data.database.gemresult.GemResult
import com.example.weightofring.data.database.gemresult.GemResultDao
import com.example.weightofring.data.database.goldprice.GoldPriceDao
import com.example.weightofring.data.database.goldprice.GoldPriceEntity
import com.example.weightofring.data.database.ringresult.RingResult
import com.example.weightofring.data.database.ringresult.RingResultDao

@Database(entities = [RingResult::class, GemResult::class, GoldPriceEntity::class], version = 1)


abstract class AppDatabase: RoomDatabase() {

    abstract fun ringResultDao(): RingResultDao
    abstract fun gemResultDao(): GemResultDao
    abstract fun goldPriceDao(): GoldPriceDao

    companion object {

        @Volatile
        private var INSTANCE: AppDatabase? = null

        fun getDatabase(context: Context): AppDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    AppDatabase::class.java,
                    "ringresult.db")
                    .build()
                INSTANCE = instance

                instance
            }
        }
    }
}