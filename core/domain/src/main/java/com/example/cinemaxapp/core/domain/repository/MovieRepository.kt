package com.example.cinemaxapp.core.domain.repository

import com.example.cinemaxapp.core.model.FeaturedBanner
import com.example.cinemaxapp.core.model.Movie
import com.example.cinemaxapp.core.model.MovieCategory

/**
 * Domain repository contract for movie data.
 */
interface MovieRepository {

    suspend fun getFeaturedBanners(): List<FeaturedBanner>

    suspend fun getMovieCategories(): List<MovieCategory>

    suspend fun getPopularMovies(genreId: String = ""): List<Movie>

    suspend fun toggleWishlist(movieId: String): Boolean
}
