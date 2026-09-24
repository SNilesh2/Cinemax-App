package com.example.cinemaxapp.core.data.network.api

import com.example.cinemaxapp.core.data.network.model.TmdbGenreResponseDto
import com.example.cinemaxapp.core.data.network.model.TmdbMovieDetailsDto
import com.example.cinemaxapp.core.data.network.model.TmdbMultiSearchResponseDto
import com.example.cinemaxapp.core.data.network.model.TmdbNowPlayingResponseDto
import org.intellij.lang.annotations.Language
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query


interface TmdbApiService {


    @GET("movie/now_playing")
    suspend fun getNowPlayingMovies(
        @Query("page") page: Int = 1,
        @Query("language") language: String = "en-US",
    ): TmdbNowPlayingResponseDto



    @GET("genre/movie/list")
    suspend fun getMovieGenres(
        @Query("language") language: String = "en-US",
    ): TmdbGenreResponseDto


    @GET("discover/movie")
    suspend fun discoverMovies(
        @Query("with_genres") withGenres: String,
        @Query("sort_by")     sortBy: String = "popularity.desc",
        @Query("language")    language: String = "en-US",
        @Query("page")        page: Int = 1,
    ): TmdbNowPlayingResponseDto


    @GET("movie/{movie_id}")
    suspend fun getMovieDetails(
        @Path("movie_id")           movieId: Int,
        @Query("append_to_response") appendToResponse: String = "credits",
        @Query("language")          language: String = "en-US",
    ): TmdbMovieDetailsDto


    @GET("search/multi")
    suspend fun searchMutli(
        @Query("query") query: String,
        @Query("page") page: Int = 1,
        @Query("language") language: String = "en-US"
    ) : TmdbMultiSearchResponseDto


    companion object {
        const val BASE_URL = "https://api.themoviedb.org/3/"
        const val IMAGE_BASE_URL_W780 = "https://image.tmdb.org/t/p/w780"
        const val IMAGE_BASE_URL_W500 = "https://image.tmdb.org/t/p/w500"

        const val IMAGE_BASE_URL_W185 = "https://image.tmdb.org/t/p/w185"

        // TMDB v3 API Key
        const val API_KEY = "538de188155ecae024321d6b2e7ad939"

        // TMDB v4 Bearer Token (API Read Access Token)
        const val BEARER_TOKEN = "eyJhbGciOiJIUzI1NiJ9.eyJhdWQiOiI1MzhkZTE4ODE1NWVjYWUwMjQzMjFkNmIyZTdhZDkzOSIsIm5iZiI6MTc4NjM0NzgwOC43MTYsInN1YiI6IjZhNzk4MTIwMGUzZDkxNWJhNDk0ODQ1MiIsInNjb3BlcyI6WyJhcGlfcmVhZCJdLCJ2ZXJzaW9uIjoxfQ.Vs5yC34X6BsgyPFlH3FsDWNbIPTJ3WyLvFDDeNDzOVw"
    }
}
