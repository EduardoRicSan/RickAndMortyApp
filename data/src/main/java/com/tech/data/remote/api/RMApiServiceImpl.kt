package com.tech.data.remote.api

import com.tech.data.remote.api.RMApiConstants.GET_ALL_CHARACTERS
import com.tech.data.remote.api.RMApiConstants.PAGE_PARAMETER
import com.tech.data.remote.dto.response.CharacterWrapperResponseDTO
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.get
import io.ktor.client.request.parameter

class RMApiServiceImpl (private val client: HttpClient) : RMApiService {
    override suspend fun getAllCharacters(page: Int): CharacterWrapperResponseDTO {
        return client.get(GET_ALL_CHARACTERS) {
            parameter(PAGE_PARAMETER, page)
        }.body()
    }

}