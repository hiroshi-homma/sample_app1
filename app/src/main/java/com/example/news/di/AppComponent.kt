package com.example.news.di

import android.app.Application
import com.example.followed.FollowedModule
import com.example.news.MainApplication
import com.example.topic.TopicModule
import com.kotlin.project.data.di.NetworkModule
import com.kotlin.project.data.di.RepositoryModule
import com.kotlin.project.data.di.RoomModule
import com.kotlin.project.domain.di.UseCaseModule
import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjector
import dagger.android.support.AndroidSupportInjectionModule
import javax.inject.Singleton

@Singleton
@Component(
    modules = [
        AndroidSupportInjectionModule::class,
        // app
        NavHostModule::class,
        ViewModelModule::class,
        FragmentModule::class,
        ActivityModule::class,
        // ui
        TopicModule::class,
        FollowedModule::class,
        // domain
        UseCaseModule::class,
        // data
        RepositoryModule::class,
        NetworkModule::class,
        RoomModule::class
    ]
)
interface AppComponent : AndroidInjector<MainApplication> {
    @Component.Builder
    interface Builder {
        @BindsInstance
        fun application(application: Application): Builder

        fun build(): AppComponent
    }

    override fun inject(application: MainApplication)
}
