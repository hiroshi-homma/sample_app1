package com.kotlin.project.data.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import com.kotlin.project.data.entities.CachedData

@Dao
interface CachedDataDao {
    @Query("SELECT * FROM cachedData")
    fun getCache(): CachedData

    @Query("DELETE FROM cachedData")
    fun deleteAll()

    @Insert
    fun insert(vararg cachedData: CachedData)

    @Delete
    fun delete(vararg cachedData: CachedData)
}
