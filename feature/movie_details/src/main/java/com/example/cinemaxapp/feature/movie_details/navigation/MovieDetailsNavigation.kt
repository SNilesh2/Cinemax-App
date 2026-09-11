package com.example.cinemaxapp.feature.movie_details.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavType
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.example.cinemaxapp.feature.movie_details.MovieDetailsScreen


object MovieDetailsRoute {
    const val ROUTE       = "movie_details/{movieId}"
    const val ARG_MOVIE_ID = "movieId"

    /** Creates a concrete route string for navigation. */
    fun createRoute(movieId: Int): String = "movie_details/$movieId"
}


fun NavController.navigateToMovieDetails(movieId: Int) {
    navigate(MovieDetailsRoute.createRoute(movieId))
}


fun NavGraphBuilder.movieDetailsScreen(navController: NavController) {
    composable(
        route     = MovieDetailsRoute.ROUTE,
        arguments = listOf(
            navArgument(MovieDetailsRoute.ARG_MOVIE_ID) {
                type = NavType.IntType
            },
        ),
    ) {
        MovieDetailsScreen(navController = navController)
    }
}
