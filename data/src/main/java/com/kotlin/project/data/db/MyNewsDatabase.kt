package com.kotlin.project.data.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.kotlin.project.data.dao.CachedDataDao
import com.kotlin.project.data.entities.CachedData

@Database(entities = [CachedData::class], version = 1)
abstract class MyNewsDatabase : RoomDatabase() {
    abstract fun cachedDataDao(): CachedDataDao
}
