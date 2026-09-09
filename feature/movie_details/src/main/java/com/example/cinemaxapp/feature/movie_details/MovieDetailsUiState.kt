package com.example.cinemaxapp.feature.movie_details

import com.example.cinemaxapp.core.model.MovieDetails


sealed class MovieDetailsUiState {


    object Loading : MovieDetailsUiState()


    data class Success(
        val movieDetails: MovieDetails,
    ) : MovieDetailsUiState()


    data class Error(
        val message: String,
    ) : MovieDetailsUiState()
}
