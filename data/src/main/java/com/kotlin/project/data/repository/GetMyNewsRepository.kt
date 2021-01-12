package com.kotlin.project.data.repository

import com.kotlin.project.data.api.MyNewsApi
import com.kotlin.project.data.model.Sections
import javax.inject.Inject

interface GetMyNewsRepository {
    suspend fun getNews(): Sections
}

internal class GetMyNewsRepositoryImpl @Inject constructor(
    private val myNewsApi: MyNewsApi
) : GetMyNewsRepository {

    override suspend fun getNews(): Sections = myNewsApi.getNews()
}
