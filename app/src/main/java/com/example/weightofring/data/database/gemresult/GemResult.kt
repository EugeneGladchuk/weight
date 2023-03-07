package com.example.weightofring.data.database.gemresult

import androidx.annotation.NonNull
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class GemResult(
    @PrimaryKey(autoGenerate = true) val id: Long? = null,
    @NonNull @ColumnInfo val cutType: String,
    @NonNull @ColumnInfo val gemDensity: String,
    @NonNull @ColumnInfo val gemLength: String,
    @NonNull @ColumnInfo val gemWidth: String,
    @NonNull @ColumnInfo val gemDepth: String,
    @NonNull @ColumnInfo val resultCarat: String,
    @NonNull @ColumnInfo val resultGram: String
)