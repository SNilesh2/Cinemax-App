package com.example.cinemaxapp.feature.home

import com.example.cinemaxapp.core.model.FeaturedBanner
import com.example.cinemaxapp.core.model.Movie
import com.example.cinemaxapp.core.model.MovieCategory


sealed interface HomeUiState {

    data object Loading : HomeUiState

    data class Success(
        // Data from GetFeaturedBannersUseCase
        val featuredBanners: List<FeaturedBanner> = emptyList(),

        // Data from GetMovieCategoriesUseCase
        val categories: List<MovieCategory> = emptyList(),

        // Data from GetPopularMoviesUseCase
        val popularMovies: List<Movie> = emptyList(),

        // UI interaction state — managed by the ViewModel, not from any use case
        val selectedCategoryId: String = "",
        val searchQuery: String = "",

        // User profile info — hardcoded for now (no user profile use case yet)
        val userName: String = "Smith",
        val userAvatarUrl: String = "https://images.unsplash.com/photo-1535713875002-d1d0cf377fde?w=200&auto=format&fit=crop&q=80",
    ) : HomeUiState

    data class Error(val message: String) : HomeUiState
}

enum class HomeBottomTab {
    HOME,
    SEARCH,
    DOWNLOAD,
    PROFILE,
}
