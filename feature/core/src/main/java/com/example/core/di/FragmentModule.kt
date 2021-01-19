package com.example.core.di

import androidx.fragment.app.FragmentFactory
import dagger.Binds
import dagger.Module

@Module
abstract class FragmentModule {
    @Binds
    abstract fun provideFragmentFactory(factory: MyFragmentFactory): FragmentFactory
}
