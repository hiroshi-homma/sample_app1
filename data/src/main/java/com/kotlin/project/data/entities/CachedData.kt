package com.kotlin.project.data.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class CachedData(
    @PrimaryKey(autoGenerate = true) val uid: Int,
    @ColumnInfo(name = "cacheJsonString") val cacheJsonString: String?
)
