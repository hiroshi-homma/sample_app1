package com.kotlin.project.domain.di

import com.kotlin.project.data.repository.CachedDataRepository
import com.kotlin.project.data.repository.GetMyNewsRepository
import com.kotlin.project.domain.usecase.CachedDataUseCase
import com.kotlin.project.domain.usecase.CachedDataUseCaseImpl
import com.kotlin.project.domain.usecase.GetMyNewsUseCase
import com.kotlin.project.domain.usecase.GetMyNewsUseCaseImpl
import dagger.Module
import dagger.Provides

@Module
class UseCaseModule {

    @Provides
    fun provideGetMasterListUseCase(
        getMyNewsRepository: GetMyNewsRepository
    ): GetMyNewsUseCase {
        return GetMyNewsUseCaseImpl(getMyNewsRepository)
    }

    @Provides
    fun provideCachedDataUseCase(
        cachedDataRepository: CachedDataRepository
    ): CachedDataUseCase {
        return CachedDataUseCaseImpl(cachedDataRepository)
    }
}
