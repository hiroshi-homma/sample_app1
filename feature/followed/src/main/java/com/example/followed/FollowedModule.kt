package com.example.followed

import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModel
import com.example.core.di.FragmentKey
import com.example.core.di.ViewModelKey
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