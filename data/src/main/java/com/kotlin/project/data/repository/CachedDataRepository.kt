package com.kotlin.project.data.repository

import com.kotlin.project.data.dao.CachedDataDao
import com.kotlin.project.data.entities.CachedData
import javax.inject.Inject

interface CachedDataRepository {
    suspend fun getCache(): CachedData
    suspend fun deleteAll()
    suspend fun insert(cachedData: CachedData)
    suspend fun delete(cachedData: CachedData)
}

class CachedDataRepositoryImpl @Inject constructor(
    private val dao: CachedDataDao
) : CachedDataRepository {

    override suspend fun getCache(): CachedData = dao.getCache()

    override suspend fun deleteAll() = dao.deleteAll()

    override suspend fun insert(cachedData: CachedData) = dao.insert(cachedData)

    override suspend fun delete(cachedData: CachedData) = dao.delete(cachedData)
}
