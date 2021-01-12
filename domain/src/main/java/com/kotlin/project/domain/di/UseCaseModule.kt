package com.kotlin.project.domain.di

import com.kotlin.project.data.repository.GetMyNewsRepository
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
}
