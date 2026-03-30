package com.tech.data.remote.api

import com.tech.data.remote.dto.response.CharacterWrapperResponseDTO

interface RMApiService {
    suspend fun getAllCharacters(
        page: Int,
    ): CharacterWrapperResponseDTO
}