package com.kotlin.project.data.di

import com.google.gson.FieldNamingPolicy
import com.google.gson.GsonBuilder
import com.kotlin.project.data.BuildConfig
import com.kotlin.project.data.api.MyNewsApi
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@Module
object NetworkModule {

    @Singleton
    @Provides
    fun provideMyNewsApi(): MyNewsApi {
        val gson = GsonBuilder()
            .setFieldNamingPolicy(FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES)
            .create()
        val httpClient = OkHttpClient.Builder()
            .connectTimeout(BuildConfig.API_CONNECT_TIMEOUT, TimeUnit.SECONDS)
            .readTimeout(BuildConfig.API_READ_TIMEOUT, TimeUnit.SECONDS)
            .addInterceptor(
                HttpLoggingInterceptor().apply {
                    if (BuildConfig.DEBUG) {
                        level = HttpLoggingInterceptor.Level.BODY
                    }
                }
            )
            .build()
        return Retrofit.Builder()
            .baseUrl(BuildConfig.DOMAIN)
            .addConverterFactory(GsonConverterFactory.create(gson))
            .client(httpClient)
            .build()
            .create(MyNewsApi::class.java)
    }
}
