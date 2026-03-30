package com.tech.design_system.components.bottomBar

import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.res.stringResource
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import com.tech.core.route.RMAppDestination
import com.tech.core.route.route
import com.tech.design_system.common.model.BottomBarItem
import com.tech.design_system.components.text.RMLabelText

@Composable
fun RMBottomBar(
    navController: NavHostController,
    items: List<BottomBarItem>
) {
    val currentRoute = currentRoute(navController)

    NavigationBar {
        items.forEach { item ->
            val route = item.destination.route()

            NavigationBarItem(
                icon = {
                    Icon(item.icon, contentDescription = null)
                },
                label = {
                    RMLabelText(text = stringResource(item.labelRes))
                },
                selected = currentRoute == route,
                onClick = {
                    if (currentRoute != route) {
                        navController.navigate(route) {
                            popUpTo(navController.graph.findStartDestination().id) {
                                saveState = true
                            }
                            launchSingleTop = true
                            restoreState = true
                        }
                    }
                }
            )
        }
    }
}

@Composable
fun currentRoute(navController: NavHostController): String? {
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    return navBackStackEntry?.destination?.route?.substringBefore("?")
}