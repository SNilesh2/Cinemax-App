package com.example.cinemaxapp.core.model


data class MovieDetails(
    val id: String,
    val title: String,
    val tagline: String,

    val posterUrl: String,

    val backdropUrl: String,

    // 4-digit year extracted from TMDB release_date, e.g. "2021"
    val releaseYear: String,

    // Formatted runtime, e.g. "148 Minutes" or "—" if unknown
    val runtime: String,

    val genres: List<String>,

    val rating: Double,

    val overview: String,


    val homepage: String,

    val cast: List<CreditPerson>,

    val crew: List<CreditPerson>,
)
