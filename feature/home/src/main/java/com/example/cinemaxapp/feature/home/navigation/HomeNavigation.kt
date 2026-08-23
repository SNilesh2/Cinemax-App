package com.example.cinemaxapp.feature.home.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.compose.composable
import com.example.cinemaxapp.feature.home.HomeScreen

object HomeRoute {
    const val ROUTE = "home"
}

fun NavController.navigateToHome(navOptions: NavOptions? = null) {
    navigate(HomeRoute.ROUTE, navOptions)
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
