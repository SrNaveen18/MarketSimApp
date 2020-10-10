package com.example.marketsimapp.ui.publicrepo

import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.example.marketsimapp.base.BaseViewModel
import com.example.marketsimapp.model.PublicRepoItem
import com.example.marketsimapp.paging.PublicRepoPagingSource
import kotlinx.coroutines.flow.Flow


class PublicRepoViewModel(private val reposRepository: PublicRepoRepository) : BaseViewModel() {

    var currentSearchResult: Flow<PagingData<PublicRepoItem>>? = null

    fun getPublicRepo(): Flow<PagingData<PublicRepoItem>> {
        val lastResult = currentSearchResult
        if (lastResult != null) {
            return lastResult
        }
        val newResult = Pager(PagingConfig(pageSize = 1)) {
            PublicRepoPagingSource(reposRepository)
        }.flow.cachedIn(viewModelScope)
        currentSearchResult = newResult
        return newResult
    }

}