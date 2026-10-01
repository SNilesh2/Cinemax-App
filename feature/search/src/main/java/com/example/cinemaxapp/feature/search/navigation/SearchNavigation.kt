package com.example.cinemaxapp.feature.search.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.compose.composable
import androidx.navigation.navOptions
import com.example.cinemaxapp.feature.search.SearchScreen


object SearchRoute {
    const val ROUTE = "search"
}


fun NavController.navigateToSearch(navOptions: NavOptions? = null) {
    val defaultNavOptions = navOptions ?: navOptions {
        popUpTo(graph.findStartDestination().id) {
            saveState = true
        }
        launchSingleTop = true
        restoreState    = true
    }
    navigate(SearchRoute.ROUTE, defaultNavOptions)
}


fun NavGraphBuilder.searchScreen(
    onMovieClick: (String) -> Unit,
) {
    composable(route = SearchRoute.ROUTE) {
        SearchScreen(
            onMovieClick = onMovieClick,
        )
    }
}
