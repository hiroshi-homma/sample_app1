package com.kotlin.project.data.repository

import com.kotlin.project.data.dao.FollowDataDao
import com.kotlin.project.data.entities.FollowData
import javax.inject.Inject

interface FollowedDataRepository {
    suspend fun getAll(): List<FollowData>
    suspend fun insert(followData: FollowData)
    suspend fun delete(followData: FollowData)
    suspend fun deleteForId(id: String)
    suspend fun updateIsFollowed(id: String, isFollowed: Boolean)
    suspend fun deleteAll()
}

class FollowedDataRepositoryImpl @Inject constructor(
    private val dao: FollowDataDao
) : FollowedDataRepository {

    override suspend fun getAll(): List<FollowData> = dao.getAll()

    override suspend fun insert(followData: FollowData) = dao.insert(followData)

    override suspend fun delete(followData: FollowData) = dao.delete(followData)

    override suspend fun deleteForId(id: String) = dao.deleteForId(id)

    override suspend fun updateIsFollowed(id: String, isFollowed: Boolean) =
        dao.upDateIsFollowedForId(id, isFollowed)

    override suspend fun deleteAll() = dao.deleteAll()
}
