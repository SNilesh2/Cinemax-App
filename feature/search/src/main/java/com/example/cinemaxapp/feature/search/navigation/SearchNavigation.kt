package com.example.cinemaxapp.feature.search.navigation

import android.net.Uri
import androidx.navigation.NavController
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.NavType
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import androidx.navigation.navOptions
import com.example.cinemaxapp.feature.search.SearchScreen


object SearchRoute {
    const val ROUTE_BASE = "search"

    const val QUERY_ARG = "query"

    const val ROUTE = "$ROUTE_BASE?$QUERY_ARG={$QUERY_ARG}"
}


fun NavController.navigateToSearch(
    query: String? = null,
    navOptions: NavOptions? = null
) {
    val route = if (!query.isNullOrBlank()) {
        "${SearchRoute.ROUTE_BASE}?${SearchRoute.QUERY_ARG}=${Uri.encode(query)}"
    } else {
        SearchRoute.ROUTE_BASE
    }

    val defaultNavOptions = navOptions ?: navOptions {
        popUpTo(graph.findStartDestination().id) {
            saveState = true
        }
        launchSingleTop = true
        restoreState    = query.isNullOrBlank()
    }
    navigate(route, defaultNavOptions)
}


fun NavGraphBuilder.searchScreen(
    onMovieClick: (String) -> Unit,
) {
    composable(
        route = SearchRoute.ROUTE,
        arguments = listOf(
            navArgument(SearchRoute.QUERY_ARG){
                type = NavType.StringType
                nullable = true
                defaultValue = null
            }
        )
    ) {
        SearchScreen(
            onMovieClick = onMovieClick,
        )
    }
}
