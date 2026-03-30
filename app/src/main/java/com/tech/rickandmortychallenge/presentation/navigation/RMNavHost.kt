package com.tech.rickandmortychallenge.presentation.navigation

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Info
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.tech.core.common.extension.toDestination
import com.tech.core.route.AboutApp
import com.tech.core.route.Characters
import com.tech.core.route.RMAppDestination
import com.tech.core.route.route
import com.tech.design_system.common.model.BottomBarItem
import com.tech.design_system.components.bottomBar.RMBottomBar
import com.tech.design_system.components.scafold.RMAppScaffold
import com.tech.design_system.components.topBar.RMTopBar
import com.tech.rickandmortychallenge.R
import com.tech.rickandmortychallenge.presentation.ui.about.AboutScreen
import com.tech.rickandmortychallenge.presentation.ui.characters.CharactersScreen
import com.tech.rickandmortychallenge.presentation.viewmodel.RMMainViewModel

@SuppressLint("UnrememberedGetBackStackEntry")
@Composable
fun RMNavHost(
    modifier: Modifier = Modifier,
    viewModel: RMMainViewModel = hiltViewModel()
) {
    val navController = rememberNavController()

    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentRoute = navBackStackEntry?.destination?.route

    val topBarTitle by viewModel.topBarTitle.collectAsStateWithLifecycle()

    val currentDestination = currentRoute.toDestination()
    val title = currentDestination?.let {
        stringResource(it.labelRes())
    } ?: ""

    LaunchedEffect(title) {
        viewModel.onRouteChanged(title)
    }

    val bottomBarItems = listOf(
        BottomBarItem(
            destination = Characters,
            icon = Icons.Default.Home,
            labelRes = R.string.characters
        ),
        BottomBarItem(
            destination = AboutApp,
            icon = Icons.Default.Info,
            labelRes = R.string.about
        )
    )

    RMAppScaffold(
        modifier = modifier.fillMaxSize(),
        topBar = {
            RMTopBar(
                titleText = topBarTitle,
            )
        },
        bottomBar = {
            RMBottomBar(
                navController = navController,
                items = bottomBarItems
            )
        }
    ) { paddingValues, showTopSnackbar, triggerLoader ->
        NavHost(
            navController = navController,
            startDestination = Characters.route(),
            modifier = Modifier.padding(paddingValues)
        ) {
            composable<Characters> {
                CharactersScreen(
                    showTopSnackbar = showTopSnackbar,
                    showLoader = triggerLoader,
                )
            }

            composable<AboutApp> {
                AboutScreen()
            }

        }
    }
}
fun RMAppDestination.labelRes(): Int = when (this) {
    Characters -> R.string.characters
    AboutApp ->  R.string.about
}



