package com.example.cinemaxapp.core.domain.repository

import com.example.cinemaxapp.core.model.FeaturedBanner
import com.example.cinemaxapp.core.model.Movie
import com.example.cinemaxapp.core.model.MovieCategory
import com.example.cinemaxapp.core.model.MovieDetails
import kotlinx.coroutines.flow.Flow


interface MovieRepository {

    // ─── GROUP 1: UI Methods — Observe Room (source of truth) ────────────────


    fun getFeaturedBanners(): Flow<List<FeaturedBanner>>


    fun getMovieCategories(): Flow<List<MovieCategory>>


    fun getPopularMovies(genreId: String = ""): Flow<List<Movie>>

    // ─── GROUP 2: Sync Methods — Fetch from TMDB, save to Room ──────────────


    suspend fun syncNowPlayingMovies()


    suspend fun syncGenres()


    suspend fun syncMoviesForGenre(genreId: String)


    // ─── GROUP 1 & 2: Movie Details ───────────────────────────────────────────

    fun getMovieDetails(movieId: Int): Flow<MovieDetails?>


    suspend fun syncMovieDetails(movieId: Int)


    suspend fun toggleWishlist(movieId: String): Boolean
}
