package com.example.cinemaxapp.feature.home

import com.example.cinemaxapp.core.model.HomeFeed

sealed interface HomeUiState {
    data object Loading : HomeUiState
    data class Success(
        val homeFeed: HomeFeed,
        val selectedCategoryId: String = "c1",
        val searchQuery: String = "",
    ) : HomeUiState
    data class Error(val message: String) : HomeUiState
}

enum class HomeBottomTab {
    HOME,
    SEARCH,
    DOWNLOAD,
    PROFILE,
}
