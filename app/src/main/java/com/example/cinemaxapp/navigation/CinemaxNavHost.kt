package com.example.cinemaxapp.navigation


import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavDestination
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.cinemaxapp.core.designsystem.icon.CinemaxIcons
import com.example.cinemaxapp.core.designsystem.theme.CinemaxTheme
import com.example.cinemaxapp.feature.auth.navigation.AuthRoute
import com.example.cinemaxapp.feature.auth.navigation.authGraph
import com.example.cinemaxapp.feature.home.HomeBottomTab
import com.example.cinemaxapp.feature.home.navigation.HomeRoute
import com.example.cinemaxapp.feature.home.navigation.homeScreen
import com.example.cinemaxapp.feature.home.navigation.navigateToHome
import com.example.cinemaxapp.feature.movie_details.navigation.movieDetailsScreen
import com.example.cinemaxapp.feature.movie_details.navigation.navigateToMovieDetails
import com.example.cinemaxapp.feature.wishlist.navigation.wishlistScreen
import com.example.cinemaxapp.feature.wishlist.navigation.navigateToWishlist
import com.example.cinemaxapp.feature.wishlist.navigation.WishlistRoute
import kotlin.collections.contains


private val TOP_LEVEL_ROUTES = setOf(
    HomeRoute.ROUTE,
    WishlistRoute.ROUTE,
    "search",
    "profile",
)


private fun NavDestination?.toHomeBottomTab(): HomeBottomTab {
    return when (this?.route) {
        HomeRoute.ROUTE -> HomeBottomTab.HOME
        WishlistRoute.ROUTE -> HomeBottomTab.WISHLIST
        "search" -> HomeBottomTab.SEARCH
        "profile" -> HomeBottomTab.PROFILE
        else -> HomeBottomTab.HOME
    }
}




@Composable
fun CinemaxNavHost(
    modifier: Modifier = Modifier,
    navController: NavHostController = rememberNavController(),
    startDestination: String = HomeRoute.ROUTE,
) {
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentDestination = navBackStackEntry?.destination
    val currentRoute = currentDestination?.route
    val isTopLevelDestination = currentRoute in TOP_LEVEL_ROUTES
    val selectedTab = currentDestination.toHomeBottomTab()

    Scaffold(
        modifier = modifier.fillMaxSize(),
        containerColor = CinemaxTheme.colors.dark,
        bottomBar = {
            if (isTopLevelDestination) {
                CinemaxBottomNavigationBar(
                    selectedTab = selectedTab,
                    onTabSelect = { tab ->
                        when (tab) {
                            HomeBottomTab.HOME -> navController.navigateToHome()
                            HomeBottomTab.WISHLIST -> navController.navigateToWishlist()
                            HomeBottomTab.SEARCH -> { /* Future search tab */ }
                            HomeBottomTab.PROFILE -> { /* Future profile tab */ }
                        }
                    }
                )
            }
        }
    ) { innerPadding ->
        NavHost(
            navController    = navController,
            startDestination = startDestination,
            modifier         = Modifier.padding(innerPadding),
        ) {
            authGraph(navController = navController)

            homeScreen(
                onMovieClick = { movieId ->
                    movieId.toIntOrNull()?.let { navController.navigateToMovieDetails(it) }
                },
                onSeeAllClick = {
                    // Future: navigate to "See All" movies screen
                },
            )

            movieDetailsScreen(navController = navController)

            wishlistScreen(
                navController = navController,
                onMovieClick  = { movieId ->
                    movieId.toIntOrNull()?.let { navController.navigateToMovieDetails(it) }
                },
            )
        }
    }
}




@Composable
private fun CinemaxBottomNavigationBar(
    selectedTab: HomeBottomTab,
    onTabSelect: (HomeBottomTab) -> Unit,
    modifier: Modifier = Modifier,
) {
    Surface(
        modifier = modifier
            .fillMaxWidth()
            .padding(horizontal = 24.dp, vertical = 20.dp)
            .height(72.dp),
        shape = RoundedCornerShape(36.dp),
        color = CinemaxTheme.colors.dark,
        tonalElevation = 8.dp,
    ) {
        Row(
            modifier = Modifier.fillMaxSize(),
            horizontalArrangement = Arrangement.SpaceAround,
            verticalAlignment = Alignment.CenterVertically,
        ) {
            // 1. Home Tab
            BottomNavItem(
                iconRes = CinemaxIcons.Home,
                label = "Home",
                isSelected = selectedTab == HomeBottomTab.HOME,
                onClick = { onTabSelect(HomeBottomTab.HOME) },
            )

            // 2. Search Tab
            BottomNavItem(
                iconRes = CinemaxIcons.Search,
                label = "Search",
                isSelected = selectedTab == HomeBottomTab.SEARCH,
                onClick = { onTabSelect(HomeBottomTab.SEARCH) },
            )

            // 3. Wishlist Tab
            BottomNavItem(
                iconRes    = CinemaxIcons.Heart,
                label      = "Wishlist",
                isSelected = selectedTab == HomeBottomTab.WISHLIST,
                onClick    = { onTabSelect(HomeBottomTab.WISHLIST) },
            )

            // 4. Profile Tab
            BottomNavItem(
                iconRes = CinemaxIcons.Profile,
                label = "Profile",
                isSelected = selectedTab == HomeBottomTab.PROFILE,
                onClick = { onTabSelect(HomeBottomTab.PROFILE) },
            )
        }
    }
}

@Composable
private fun BottomNavItem(
    iconRes: Int,
    label: String?,
    isSelected: Boolean,
    onClick: () -> Unit,
) {
    if (isSelected && label != null) {
        Surface(
            shape = RoundedCornerShape(20.dp),
            color = CinemaxTheme.colors.soft,
            modifier = Modifier.clickable { onClick() },
        ) {
            Row(
                modifier = Modifier.padding(horizontal = 16.dp, vertical = 10.dp),
                verticalAlignment = Alignment.CenterVertically,
            ) {
                Icon(
                    painter = painterResource(id = iconRes),
                    contentDescription = label,
                    tint = CinemaxTheme.colors.blueAccent,
                    modifier = Modifier.size(20.dp),
                )
                Spacer(modifier = Modifier.width(8.dp))
                Text(
                    text = label,
                    style = CinemaxTheme.typography.h5SemiBold,
                    color = CinemaxTheme.colors.blueAccent,
                )
            }
        }
    } else {
        IconButton(onClick = onClick) {
            Icon(
                painter = painterResource(id = iconRes),
                contentDescription = null,
                tint = if (isSelected) CinemaxTheme.colors.blueAccent else CinemaxTheme.colors.grey,
                modifier = Modifier.size(22.dp),
            )
        }
    }
}
