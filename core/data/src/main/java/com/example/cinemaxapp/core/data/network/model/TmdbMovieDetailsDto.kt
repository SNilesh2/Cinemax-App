package com.example.cinemaxapp.core.data.network.model

import com.google.gson.annotations.SerializedName


data class TmdbMovieDetailsDto(
    @SerializedName("id")           val id: Int,
    @SerializedName("title")        val title: String,
    @SerializedName("overview")     val overview: String?,
    @SerializedName("poster_path")  val posterPath: String?,
    @SerializedName("backdrop_path") val backdropPath: String?,
    @SerializedName("release_date") val releaseDate: String?,
    @SerializedName("runtime")      val runtime: Int?,
    @SerializedName("vote_average") val voteAverage: Double?,
    @SerializedName("vote_count")   val voteCount: Int?,
    @SerializedName("tagline")      val tagline: String?,

    // website link provided by TMDB
    @SerializedName("homepage")     val homepage: String?,

    // returns a list of genres
    @SerializedName("genres")       val genres: List<TmdbGenreDto> = emptyList(),


    @SerializedName("credits")      val credits: TmdbCreditsDto?,
)
