package com.kotlin.project.data.di

import android.app.Application
import androidx.room.Room
import com.kotlin.project.data.db.MyNewsDatabase
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class RoomModule {

    @Singleton
    @Provides
    internal fun provideMyNewsDatabase(app: Application): MyNewsDatabase =
        Room.databaseBuilder(app, MyNewsDatabase::class.java, "park.db")
            .fallbackToDestructiveMigration()
            .build()

    @Singleton
    @Provides
    internal fun provideCachedDataDao(db: MyNewsDatabase) =
        db.cachedDataDao()
}
