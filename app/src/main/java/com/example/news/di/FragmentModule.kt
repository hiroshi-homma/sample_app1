package com.example.news.di

import androidx.fragment.app.FragmentFactory
import dagger.Binds
import dagger.Module

@Module
internal abstract class FragmentModule {
    @Binds
    abstract fun provideFragmentFactory(factory: MyFragmentFactory): FragmentFactory
}
