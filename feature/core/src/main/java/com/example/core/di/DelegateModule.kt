package com.example.core.di

import com.example.core.ui.ViewModelDelegate
import com.example.core.ui.ViewModelDelegateImpl
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class DelegateModule {

    @Singleton
    @Provides
    fun provideViewModelDelegate(): ViewModelDelegate {
        return ViewModelDelegateImpl()
    }
}
