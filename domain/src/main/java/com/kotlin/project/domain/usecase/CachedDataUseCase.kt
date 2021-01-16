package com.kotlin.project.domain.usecase

import com.kotlin.project.data.entities.CachedData
import com.kotlin.project.data.repository.CachedDataRepository
import javax.inject.Inject

interface CachedDataUseCase {
    suspend fun getCache(): CachedData?
    suspend fun updateCache(id: Long, updateString: String)
    suspend fun deleteAll()
    suspend fun insert(cachedData: CachedData)
    suspend fun delete(cachedData: CachedData)
}

class CachedDataUseCaseImpl @Inject constructor(
    private val repository: CachedDataRepository
) : CachedDataUseCase {
    override suspend fun getCache(): CachedData? = repository.getCache()
    override suspend fun insert(cachedData: CachedData) = repository.insert(cachedData)
    override suspend fun updateCache(id: Long, updateString: String) = repository.updateCache(id, updateString)
    override suspend fun delete(cachedData: CachedData) = repository.delete(cachedData)
    override suspend fun deleteAll() = repository.deleteAll()
}
