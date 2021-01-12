package com.kotlin.project.domain.usecase

import com.kotlin.project.data.model.MyNewsError
import com.kotlin.project.data.model.Result
import com.kotlin.project.data.model.Sections
import com.kotlin.project.data.repository.GetMyNewsRepository
import javax.inject.Inject

interface GetMyNewsUseCase {
    suspend fun getNews(): Result<Sections>
}

class GetMyNewsUseCaseImpl @Inject constructor(
    private val getMasterListRepository: GetMyNewsRepository
) : GetMyNewsUseCase {
    override suspend fun getNews(): Result<Sections> =
        runCatching { getMasterListRepository.getNews() }
            .fold(
                onSuccess = { return Result.Success(it) },
                onFailure = { return Result.Error(MyNewsError.UnrecognizedError()) }
            )
}
