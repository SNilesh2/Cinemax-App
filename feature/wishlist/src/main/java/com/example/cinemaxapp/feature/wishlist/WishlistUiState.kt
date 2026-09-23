package com.example.cinemaxapp.feature.wishlist

import com.example.cinemaxapp.core.model.Movie


sealed interface WishlistUiState {


    data object Loading : WishlistUiState


    data object Empty : WishlistUiState


    data class Success(
        val movies: List<Movie>,
    ) : WishlistUiState


    data class Error(
        val message: String,
    ) : WishlistUiState
}
