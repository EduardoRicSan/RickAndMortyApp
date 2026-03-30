package com.tech.rickandmortychallenge.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.tech.domain.model.CharacterModel
import com.tech.domain.useCase.GetAllCharactersUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

@HiltViewModel
class CharactersViewModel @Inject constructor(
    private val getAllCharactersUseCase: GetAllCharactersUseCase
) : ViewModel() {

    /**
     * Stream de PagingData para UI
     */
    val characters: Flow<PagingData<CharacterModel>> =
        getAllCharactersUseCase()
            .cachedIn(viewModelScope)
}