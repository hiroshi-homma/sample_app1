package com.example.news.di

import com.example.core.ui.MyNewsActivity
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
internal abstract class ActivityModule {
    @ContributesAndroidInjector
    abstract fun contributeMyNewsActivity(): MyNewsActivity
}
