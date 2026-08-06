package com.example.cinemaxapp.navigation


import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.rememberNavController
import com.example.cinemaxapp.feature.auth.navigation.AuthRoute
import com.example.cinemaxapp.feature.auth.navigation.authGraph


@Composable
fun CinemaxNavHost(
    modifier: Modifier = Modifier,
    navController: NavHostController = rememberNavController(),
    startDestination: String = AuthRoute.ROOT,
) {
    NavHost(
        navController = navController,
        startDestination = startDestination,
        modifier = modifier,
    ) {
        authGraph(navController = navController)
    }
}
