package com.tech.domain.repository

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.tech.data.remote.api.RMApiService
import com.tech.domain.model.CharacterModel
import com.tech.domain.paging.CharacterPagingSource
import jakarta.inject.Inject
import kotlinx.coroutines.flow.Flow

class RMRepositoryImpl @Inject constructor(
    private val apiService: RMApiService
) : RMRepository {

    override fun getAllCharacters(): Flow<PagingData<CharacterModel>> {
        return Pager(
            config = PagingConfig(
                pageSize = 20,
                prefetchDistance = 5
            ),
            pagingSourceFactory = {
                CharacterPagingSource(apiService)
            }
        ).flow
    }
}