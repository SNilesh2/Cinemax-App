package com.example.cinemaxapp.core.model


data class Movie(
    val id: String,
    val title: String,
    val posterUrl: String,
    val rating: Double,
    val category: String,
    val releaseDate: String = "",
    val isWishlisted: Boolean = false,
)


data class FeaturedBanner(
    val id: String,
    val title: String,
    val releaseDateText: String,
    val bannerImageUrl: String,
)


data class MovieCategory(
    val id: String,
    val name: String,
)

