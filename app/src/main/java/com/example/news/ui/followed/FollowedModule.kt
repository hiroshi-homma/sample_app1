package com.example.news.ui.followed

import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModel
import com.example.news.di.ViewModelKey
import com.example.news.di.FragmentKey
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
abstract class FollowedModule {
    @Binds
    @IntoMap
    @FragmentKey(FollowedFragment::class)
    abstract fun provideFollowedFragment(fragment: FollowedFragment): Fragment

    @Binds
    @IntoMap
    @ViewModelKey(FollowedViewModel::class)
    abstract fun provideFollowedViewModel(viewModel: FollowedViewModel): ViewModel
}