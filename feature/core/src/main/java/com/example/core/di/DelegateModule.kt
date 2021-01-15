package com.example.core.di

import com.example.core.delegate.TopicAndFollowedDelegate
import com.example.core.delegate.TopicAndFollowedDelegateImpl
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class DelegateModule {

    @Singleton
    @Provides
    fun provideTopicAndFollowedDelegate(): TopicAndFollowedDelegate {
        return TopicAndFollowedDelegateImpl()
    }
}
