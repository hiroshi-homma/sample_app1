package com.kotlin.project.data.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import com.kotlin.project.data.entities.FollowData

@Dao
interface FollowDataDao {
    @Query("SELECT * FROM followData")
    fun getAll(): List<FollowData>

    @Query("SELECT * FROM followData WHERE uid IN (:userIds)")
    fun loadAllByIds(userIds: IntArray): List<FollowData>

    @Query("SELECT * FROM followData WHERE id LIKE (:id)")
    fun findById(id: String): FollowData

    @Query("SELECT * FROM followData WHERE label LIKE (:label)")
    fun findByLabel(label: String): FollowData

    @Query("DELETE FROM followData WHERE id = (:id)")
    fun deleteForId(id: String)

    @Query("UPDATE followData SET is_followed = (:isFollowed) WHERE id = (:id)")
    fun upDateIsFollowedForId(id: String, isFollowed: Boolean)

    @Query("DELETE FROM followData")
    fun deleteAll()

    @Insert
    fun insert(vararg followData: FollowData)

    @Delete
    fun delete(vararg followData: FollowData)
}
