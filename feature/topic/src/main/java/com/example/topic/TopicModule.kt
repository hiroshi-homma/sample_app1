package com.example.topic

import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModel
import com.example.core.di.FragmentKey
import com.example.core.di.ViewModelKey
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
abstract class TopicModule {
    @Binds
    @IntoMap
    @FragmentKey(TopicFragment::class)
    abstract fun provideTopicFragment(fragment: TopicFragment): Fragment

    @Binds
    @IntoMap
    @ViewModelKey(TopicViewModel::class)
    abstract fun provideTopicViewModel(viewModel: TopicViewModel): ViewModel
}