package com.example.cinemaxapp.feature.auth.navigation

import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.example.cinemaxapp.feature.auth.loginsignup.LoginSignupScreen

const val AUTH_GRAPH_ROUTE = "auth_graph"

fun NavGraphBuilder.authGraph() {
    composable(AUTH_GRAPH_ROUTE) {
        LoginSignupScreen()
    }
}
