package com.example.cinemaxapp.core.data.network.model

import com.google.gson.annotations.SerializedName

/**
 * DTO matching TMDB `GET 3/movie/now_playing` API response payload.
 */
data class TmdbNowPlayingResponseDto(
    @SerializedName("page") val page: Int? = null,
    @SerializedName("results") val results: List<TmdbMovieDto>? = null,
    @SerializedName("dates") val dates: TmdbDatesDto? = null,
    @SerializedName("total_pages") val totalPages: Int? = null,
    @SerializedName("total_results") val totalResults: Int? = null,
)

data class TmdbDatesDto(
    @SerializedName("maximum") val maximum: String? = null,
    @SerializedName("minimum") val minimum: String? = null,
)

data class TmdbMovieDto(
    @SerializedName("id") val id: Int,
    @SerializedName("title") val title: String,
    @SerializedName("original_title") val originalTitle: String? = null,
    @SerializedName("overview") val overview: String? = null,
    @SerializedName("poster_path") val posterPath: String? = null,
    @SerializedName("backdrop_path") val backdropPath: String? = null,
    @SerializedName("release_date") val releaseDate: String? = null,
    @SerializedName("vote_average") val voteAverage: Double? = null,
    @SerializedName("vote_count") val voteCount: Int? = null,
    @SerializedName("popularity") val popularity: Double? = null,
    @SerializedName("genre_ids") val genreIds: List<Int>? = null,
)
