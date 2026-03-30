package com.tech.data.remote.dataSource

import com.tech.core.network.NetworkResult
import com.tech.core.network.safeApiCall
import com.tech.data.remote.api.RMApiService
import com.tech.data.remote.dto.response.CharacterWrapperResponseDTO
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOn

class RMDataSourceImpl(
    private val apiService: RMApiService
) : RMDataSource {

    override suspend fun getAllCharacters(page: Int): Flow<NetworkResult<CharacterWrapperResponseDTO>> =
        safeApiCall {
            apiService.getAllCharacters(page)
        }.flowOn(Dispatchers.IO)


}