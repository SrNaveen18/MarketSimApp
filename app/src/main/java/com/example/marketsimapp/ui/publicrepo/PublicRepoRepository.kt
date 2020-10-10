package com.example.marketsimapp.ui.publicrepo

import com.example.marketsimapp.model.PublicRepoItem
import com.example.marketsimapp.webservices.ApiStories
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class PublicRepoRepository(private val apiStories: ApiStories) {
    suspend fun getRepoList(page: Int): List<PublicRepoItem> {
        return withContext(Dispatchers.IO) {
            apiStories.getRepoList(
                page = page
            )
        }
    }
}