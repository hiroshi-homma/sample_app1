package com.example.core.di

import com.example.core.InjectingNavHostFragment
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class NavHostModule {
    @ContributesAndroidInjector(modules = [FragmentModule::class])
    abstract fun navHostFragmentInjector(): InjectingNavHostFragment
}
