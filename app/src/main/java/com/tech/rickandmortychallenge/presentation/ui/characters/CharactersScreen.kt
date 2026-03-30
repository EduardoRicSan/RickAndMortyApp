package com.tech.rickandmortychallenge.presentation.ui.characters

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.GridItemSpan
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import androidx.paging.LoadState
import androidx.paging.compose.collectAsLazyPagingItems
import com.tech.design_system.common.model.CharacterStatus
import com.tech.design_system.common.model.RMSnackbarMessage
import com.tech.design_system.common.model.RMSnackbarType
import com.tech.design_system.components.card.RMImageCard
import com.tech.design_system.components.chip.RMChip
import com.tech.design_system.components.loader.RMSimpleLoadingIndicator
import com.tech.design_system.components.text.RMCaptionText
import com.tech.design_system.components.text.RMTitleText
import com.tech.design_system.tokens.spacing
import com.tech.domain.model.CharacterModel
import com.tech.rickandmortychallenge.presentation.ui.state.RMErrorState
import com.tech.rickandmortychallenge.presentation.viewmodel.CharactersViewModel

@Composable
fun CharactersScreen(
    viewModel: CharactersViewModel = hiltViewModel(),
    showTopSnackbar: (RMSnackbarMessage) -> Unit,
    showLoader: (Boolean) -> Unit,
) {

    val characters = viewModel.characters.collectAsLazyPagingItems()

    val isRefreshing = characters.loadState.refresh is LoadState.Loading

    LaunchedEffect(isRefreshing) {
        showLoader(isRefreshing)
    }

    Box(modifier = Modifier.fillMaxSize()) {

        when (val refreshState = characters.loadState.refresh) {

            is LoadState.Error -> {
                RMErrorState(
                    message = refreshState.error.message ?: "Error loading characters",
                    onRetry = { characters.retry() }
                )
            }

            is LoadState.NotLoading -> {
                LazyVerticalGrid(
                    columns = GridCells.Fixed(2),
                    modifier = Modifier.fillMaxSize(),
                    contentPadding = PaddingValues(MaterialTheme.spacing.md),
                    verticalArrangement = Arrangement.spacedBy(MaterialTheme.spacing.md),
                    horizontalArrangement = Arrangement.spacedBy(MaterialTheme.spacing.md)
                ) {

                    items(
                        count = characters.itemCount,
                        key = { index -> characters[index]?.id ?: index }
                    ) { index ->

                        val character = characters[index] ?: return@items
                        CharacterItem(character)
                    }

                    // Loader paginación
                    item(span = { GridItemSpan(2) }) {
                        if (characters.loadState.append is LoadState.Loading) {
                            Box(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(MaterialTheme.spacing.md),
                                contentAlignment = Alignment.Center
                            ) {
                                RMSimpleLoadingIndicator()
                            }
                        }
                    }
                }
            }

            else -> Unit
        }
    }
}

@Composable
fun CharacterItem(
    character: CharacterModel,
    modifier: Modifier = Modifier,
    onClick: (() -> Unit)? = null
) {
    RMImageCard(
        imageUrl = character.image,
        modifier = modifier,
        onClick = onClick
    ) {

        RMChip(
            status = CharacterStatus.from(character.status),
            modifier = Modifier
                .align(Alignment.TopEnd)
                .padding(8.dp)
        )

        // 🔽 INFO (abajo)
        Column(
            modifier = Modifier
                .align(Alignment.BottomStart)
                .padding(MaterialTheme.spacing.sm)
        ) {

            RMTitleText(
                text = character.name.orEmpty(),
                color = Color.White,
                maxLines = 1
            )

            RMCaptionText(
                text = buildString {
                    append(character.species.orEmpty())
                },
                color = Color.White.copy(alpha = 0.85f),
                maxLines = 1
            )

            RMCaptionText(
                text = buildString {
                    if (!character.gender.isNullOrEmpty()) {
                        append(character.gender)
                    }
                    if (!character.type.isNullOrEmpty()) {
                        append(" • ${character.type}")
                    }
                },
                color = Color.White.copy(alpha = 0.75f),
                maxLines = 1
            )
        }
    }
}