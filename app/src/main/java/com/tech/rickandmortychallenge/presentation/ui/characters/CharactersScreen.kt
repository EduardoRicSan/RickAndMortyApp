package com.tech.rickandmortychallenge.presentation.ui.characters

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import com.tech.design_system.common.model.RMSnackbarMessage

@Composable
fun CharactersScreen(
    //viewModel: CharactersViewModel = hiltViewModel(),
    showTopSnackbar: (RMSnackbarMessage) -> Unit,
    showLoader: (Boolean) -> Unit,
    ) {

    Text(text = "Characters Screen")
}