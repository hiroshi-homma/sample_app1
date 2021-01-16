package com.kotlin.project.data.di

import com.kotlin.project.data.api.MyNewsApi
import com.kotlin.project.data.dao.CachedDataDao
import com.kotlin.project.data.repository.CachedDataRepository
import com.kotlin.project.data.repository.CachedDataRepositoryImpl
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
    fun provideCachedDataRepository(dao: CachedDataDao): CachedDataRepository {
        return CachedDataRepositoryImpl(dao)
    }
}
