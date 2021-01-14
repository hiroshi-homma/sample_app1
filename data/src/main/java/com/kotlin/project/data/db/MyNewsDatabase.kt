package com.kotlin.project.data.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.kotlin.project.data.dao.FollowDataDao
import com.kotlin.project.data.entities.FollowData

@Database(entities = [FollowData::class], version = 1)
abstract class MyNewsDatabase : RoomDatabase() {
    abstract fun followDataDao(): FollowDataDao
}
