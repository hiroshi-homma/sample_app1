package com.example.news

import com.example.news.di.DaggerAppComponent
import dagger.android.AndroidInjector
import dagger.android.DaggerApplication
import timber.log.Timber
import timber.log.Timber.DebugTree

class MainApplication : DaggerApplication() {

    override fun onCreate() {
        super.onCreate()
        setupDagger()
        if (BuildConfig.DEBUG) {
            Timber.plant(DebugTree())
        }
    }

    override fun applicationInjector(): AndroidInjector<out DaggerApplication> {
        return DaggerAppComponent.builder().application(this).build()
    }

    private fun setupDagger() {
        DaggerAppComponent.builder().application(this).build().inject(this)
    }
}
