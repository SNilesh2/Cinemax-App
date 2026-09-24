package com.example.cinemaxapp.core.data.network.model

import com.google.gson.annotations.SerializedName

data class TmdbMultiSearchResponseDto(
    @SerializedName("page") val page: Int? = null,
    @SerializedName("results") val results: List<TmdbSearchResultDto>? = null,
    @SerializedName("total_pages") val totalPages: Int? = null,
    @SerializedName("total_results") val totalResults: Int? = null,
)



data class TmdbSearchResultDto(
    @SerializedName("id") val id: Int,
    @SerializedName("media_type") val mediaType: String? = null,

    // ─── Movie fields ─────────────────────────────────────────
    @SerializedName("title") val title: String? = null,
    @SerializedName("original_title") val originalTitle: String? = null,
    @SerializedName("poster_path") val posterPath: String? = null,
    @SerializedName("backdrop_path") val backdropPath: String? = null,
    @SerializedName("release_date") val releaseDate: String? = null,
    @SerializedName("vote_average") val voteAverage: Double? = null,
    @SerializedName("vote_count") val voteCount: Int? = null,
    @SerializedName("popularity") val popularity: Double? = null,
    @SerializedName("genre_ids") val genreIds: List<Int>? = null,
    @SerializedName("overview") val overview: String? = null,

    // ─── Person fields ────────────────────────────────────────
    @SerializedName("name") val name: String? = null,
    @SerializedName("profile_path") val profilePath: String? = null,
    @SerializedName("known_for_department") val knownForDepartment: String? = null,
)