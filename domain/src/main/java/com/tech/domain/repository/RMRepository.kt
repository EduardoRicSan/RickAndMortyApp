package com.tech.domain.repository

import androidx.paging.PagingData
import com.tech.domain.model.CharacterModel
import kotlinx.coroutines.flow.Flow

interface RMRepository {
    fun getAllCharacters(): Flow<PagingData<CharacterModel>>
}