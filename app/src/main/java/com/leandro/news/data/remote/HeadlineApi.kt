package com.leandro.news.data.remote

import com.leandro.news.data.response.HeadlineResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface HeadlineApi {

    @GET("top-headlines")
    suspend fun getTopHeadlines(
        @Query("sources") source: String?
    ) : HeadlineResponse
}