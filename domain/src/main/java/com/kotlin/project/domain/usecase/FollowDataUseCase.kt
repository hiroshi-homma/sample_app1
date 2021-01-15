package com.kotlin.project.domain.usecase

import com.kotlin.project.data.entities.FollowData
import com.kotlin.project.data.model.MyNewsError
import com.kotlin.project.data.model.Result
import com.kotlin.project.data.repository.FollowedDataRepository
import javax.inject.Inject

interface FollowDataUseCase {
    suspend fun getAll(): Result<List<FollowData>>
    suspend fun insert(followData: FollowData)
    suspend fun delete(followData: FollowData)
    suspend fun deleteForId(id: String)
    suspend fun updateIsFollowed(id: String, isFollowed: Boolean)
    suspend fun deleteAll()
}

class FollowDataUseCaseImpl @Inject constructor(
    private val followedDataRepository: FollowedDataRepository
) : FollowDataUseCase {
    override suspend fun getAll(): Result<List<FollowData>> =
        runCatching { followedDataRepository.getAll() }
            .fold(
                onSuccess = { return Result.Success(it) },
                onFailure = { return Result.Error(MyNewsError.UnrecognizedError()) }
            )

    override suspend fun insert(followData: FollowData) = followedDataRepository.insert(followData)

    override suspend fun delete(followData: FollowData) = followedDataRepository.delete(followData)

    override suspend fun deleteForId(id: String) = followedDataRepository.deleteForId(id)

    override suspend fun updateIsFollowed(id: String, isFollowed: Boolean) =
        followedDataRepository.upDateIsFollowedForId(id, isFollowed)

    override suspend fun deleteAll() = followedDataRepository.deleteAll()
}
