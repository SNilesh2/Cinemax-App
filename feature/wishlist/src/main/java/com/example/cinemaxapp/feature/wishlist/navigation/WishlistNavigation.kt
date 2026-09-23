package com.example.cinemaxapp.feature.wishlist.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.compose.composable
import androidx.navigation.navOptions
import com.example.cinemaxapp.feature.wishlist.WishlistScreen


object WishlistRoute {
    const val ROUTE = "wishlist"
}


fun NavController.navigateToWishlist(navOptions: NavOptions? = null) {
    val defaultNavOptions = navOptions ?: navOptions{
        popUpTo(graph.findStartDestination().id){
            saveState = true
        }
        launchSingleTop = true
        restoreState = true
    }
    navigate(WishlistRoute.ROUTE, defaultNavOptions)
}


fun NavGraphBuilder.wishlistScreen(
    navController: NavController,
    onMovieClick: (String) -> Unit,
) {
    composable(route = WishlistRoute.ROUTE) {
        WishlistScreen(
            onBackClick  = { navController.popBackStack() },
            onMovieClick = onMovieClick,
        )
    }
}
