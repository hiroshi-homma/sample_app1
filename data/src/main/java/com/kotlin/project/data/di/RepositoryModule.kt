package com.kotlin.project.data.di

import com.kotlin.project.data.api.MyNewsApi
import com.kotlin.project.data.dao.FollowDataDao
import com.kotlin.project.data.repository.FollowedDataRepository
import com.kotlin.project.data.repository.FollowedDataRepositoryImpl
import com.kotlin.project.data.repository.GetMyNewsRepository
import com.kotlin.project.data.repository.GetMyNewsRepositoryImpl
import dagger.Module
import dagger.Provides

@Module
class RepositoryModule {
    @Provides
    fun provideGetMasterListRepository(myNewsApi: MyNewsApi): GetMyNewsRepository {
        return GetMyNewsRepositoryImpl(myNewsApi)
    }

    @Provides
    fun provideFollowedDataRepository(dao: FollowDataDao): FollowedDataRepository {
        return FollowedDataRepositoryImpl(dao)
    }
}
