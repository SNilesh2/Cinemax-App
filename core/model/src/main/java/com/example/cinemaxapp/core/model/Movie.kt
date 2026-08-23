package com.example.cinemaxapp.core.model

/**
 * Domain representation of a movie item.
 */
data class Movie(
    val id: String,
    val title: String,
    val posterUrl: String,
    val rating: Double,
    val category: String,
    val releaseDate: String = "",
    val isWishlisted: Boolean = false,
)

/**
 * Domain representation of a featured banner item in the home carousel.
 */
data class FeaturedBanner(
    val id: String,
    val title: String,
    val releaseDateText: String,
    val bannerImageUrl: String,
)

/**
 * Movie category filter item.
 */
data class MovieCategory(
    val id: String,
    val name: String,
)

/**
 * Combined home feed data state.
 */
data class HomeFeed(
    val userName: String = "Smith",
    val userAvatarUrl: String = "",
    val featuredBanners: List<FeaturedBanner> = emptyList(),
    val categories: List<MovieCategory> = emptyList(),
    val popularMovies: List<Movie> = emptyList(),
)
