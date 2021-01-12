package com.kotlin.project.data.api

import com.kotlin.project.data.model.Sections
import retrofit2.http.GET

interface MyNewsApi {
    @GET("followables.json")
    suspend fun getNews(): Sections
}
