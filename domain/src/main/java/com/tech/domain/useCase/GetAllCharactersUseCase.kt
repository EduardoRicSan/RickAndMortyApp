package com.tech.domain.useCase

import androidx.paging.PagingData
import com.tech.domain.model.CharacterModel
import com.tech.domain.repository.RMRepository
import jakarta.inject.Inject
import kotlinx.coroutines.flow.Flow

class GetAllCharactersUseCase @Inject constructor(
    private val repository: RMRepository
) {
    operator fun invoke(): Flow<PagingData<CharacterModel>> {
        return repository.getAllCharacters()
    }
}