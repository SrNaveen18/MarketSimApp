package com.example.marketsimapp.paging

import androidx.paging.PagingSource
import com.example.marketsimapp.model.PublicRepoItem
import com.example.marketsimapp.ui.publicrepo.PublicRepoRepository

class PublicRepoPagingSource(private val repository: PublicRepoRepository) :
    PagingSource<Int, PublicRepoItem>() {
    private val initialPageIndex: Int = 1
    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, PublicRepoItem> {
        return try {
            val position = params.key ?: initialPageIndex
            val repoList = repository.getRepoList(page = position)
            LoadResult.Page(
                data = repoList,
                prevKey = if (position == initialPageIndex) null else position - 1,
                nextKey = if (repoList.isNullOrEmpty()) null else position + 1
            )
        } catch (e: Exception) {
            LoadResult.Error(e)
        }
    }
}
