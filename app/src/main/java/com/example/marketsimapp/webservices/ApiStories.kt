package com.example.marketsimapp.webservices

import com.example.marketsimapp.model.PublicRepoItem
import retrofit2.http.GET
import retrofit2.http.Query

const val BASE_URL = "https://api.github.com/"

interface ApiStories {
    @GET("repositories")
    suspend fun getRepoList(
        @Query("page") page: Int): List<PublicRepoItem>

}

