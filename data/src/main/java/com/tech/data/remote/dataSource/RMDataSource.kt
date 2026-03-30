package com.tech.data.remote.dataSource

import com.tech.core.network.NetworkResult
import com.tech.data.remote.dto.response.CharacterWrapperResponseDTO
import kotlinx.coroutines.flow.Flow

interface RMDataSource {
    suspend fun getAllCharacters(
        page: Int,
    ): Flow<NetworkResult<CharacterWrapperResponseDTO>>
}