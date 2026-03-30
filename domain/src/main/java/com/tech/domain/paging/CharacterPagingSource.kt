package com.tech.domain.paging

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.tech.data.remote.api.RMApiService
import com.tech.domain.model.CharacterModel
import com.tech.domain.model.toDomain

class CharacterPagingSource(
    private val apiService: RMApiService
) : PagingSource<Int, CharacterModel>() {

    override fun getRefreshKey(state: PagingState<Int, CharacterModel>): Int? {
        return state.anchorPosition?.let { position ->
            state.closestPageToPosition(position)?.prevKey?.plus(1)
                ?: state.closestPageToPosition(position)?.nextKey?.minus(1)
        }
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, CharacterModel> {
        return try {
            val page = params.key ?: 1
            val response = apiService.getAllCharacters(page)
            LoadResult.Page(
                data = response.results.map { it.toDomain() },
                prevKey = if (page == 1) null else page - 1,
                nextKey = if (response.info?.next != null) page + 1 else null
            )
        } catch (e: Throwable) {
            LoadResult.Error(e)
        }
    }
}