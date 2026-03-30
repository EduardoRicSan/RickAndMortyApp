package com.tech.rickandmortychallenge.presentation.viewmodel

import androidx.lifecycle.ViewModel
import com.tech.domain.useCase.GetAllCharactersUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import javax.inject.Inject

@HiltViewModel
class RMMainViewModel @Inject constructor(
    private val getAllCharactersUseCase: GetAllCharactersUseCase
) : ViewModel() {

    // ----------------------------
    // 🔹 UI State
    // ----------------------------
    private val _topBarTitle = MutableStateFlow("")
    val topBarTitle: StateFlow<String> = _topBarTitle.asStateFlow()


    // ----------------------------
    // 🔹 UI Controls
    // ----------------------------
    fun onRouteChanged(route: String?) {
        _topBarTitle.value = route ?: ""

    }

}