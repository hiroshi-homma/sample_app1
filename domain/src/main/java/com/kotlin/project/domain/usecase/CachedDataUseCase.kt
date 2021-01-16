package com.kotlin.project.domain.usecase

import com.kotlin.project.data.entities.CachedData
import com.kotlin.project.data.repository.CachedDataRepository
import javax.inject.Inject

interface CachedDataUseCase {
    suspend fun getCache(): CachedData?
    suspend fun deleteAll()
    suspend fun insert(cachedData: CachedData)
    suspend fun delete(cachedData: CachedData)
}

class CachedDataUseCaseImpl @Inject constructor(
    private val cachedDataRepository: CachedDataRepository
) : CachedDataUseCase {
    override suspend fun getCache(): CachedData? = cachedDataRepository.getCache()
    override suspend fun insert(cachedData: CachedData) = cachedDataRepository.insert(cachedData)
    override suspend fun delete(cachedData: CachedData) = cachedDataRepository.delete(cachedData)
    override suspend fun deleteAll() = cachedDataRepository.deleteAll()
}
