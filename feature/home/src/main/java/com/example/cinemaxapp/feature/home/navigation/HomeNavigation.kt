package com.example.cinemaxapp.feature.home.navigation

import androidx.compose.runtime.getValue
import androidx.navigation.NavController
import androidx.navigation.NavDestination
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.navOptions
import com.example.cinemaxapp.feature.home.HomeBottomTab
import com.example.cinemaxapp.feature.home.HomeScreen

object HomeRoute {
    const val ROUTE = "home"
}


fun NavController.navigateToHome(navOptions: NavOptions? = null) {
    val defaultNavOptions = navOptions ?: navOptions{
        popUpTo(graph.findStartDestination().id)
        {
            saveState = true
        }
        launchSingleTop = true
        restoreState = true
    }
    navigate(HomeRoute.ROUTE, defaultNavOptions)
}

fun NavGraphBuilder.homeScreen(
    onMovieClick: (String) -> Unit = {},
    onSeeAllClick: () -> Unit = {},
) {
    composable(route = HomeRoute.ROUTE) {

        HomeScreen(
            onMovieClick = onMovieClick,
            onSeeAllClick = onSeeAllClick,
        )
    }
}
