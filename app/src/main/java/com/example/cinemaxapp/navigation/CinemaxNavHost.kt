package com.example.cinemaxapp.navigation


import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.rememberNavController
import com.example.cinemaxapp.feature.auth.navigation.AuthRoute
import com.example.cinemaxapp.feature.auth.navigation.authGraph
import com.example.cinemaxapp.feature.home.navigation.HomeRoute
import com.example.cinemaxapp.feature.home.navigation.homeScreen

@Composable
fun CinemaxNavHost(
    modifier: Modifier = Modifier,
    navController: NavHostController = rememberNavController(),
    startDestination: String = HomeRoute.ROUTE,
) {
    NavHost(
        navController = navController,
        startDestination = startDestination,
        modifier = modifier,
    ) {
        authGraph(navController = navController)

        homeScreen(
            onMovieClick = { movieId ->
                // Navigate to Movie Details in future feature
            },
            onSeeAllClick = {
                // Navigate to See All Movies in future feature
            },
        )
    }
}
