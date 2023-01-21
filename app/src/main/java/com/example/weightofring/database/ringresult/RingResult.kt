package com.example.weightofring.database.ringresult

import androidx.annotation.NonNull
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class RingResult(
    @PrimaryKey(autoGenerate = true) val id: Int? = null,
    @NonNull @ColumnInfo val width: String,
    @NonNull @ColumnInfo val size: String,
    @NonNull @ColumnInfo val thickness: String,
    @NonNull @ColumnInfo val type: String,
    @NonNull @ColumnInfo val result: String
)