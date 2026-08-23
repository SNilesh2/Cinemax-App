package com.example.cinemaxapp.core.data.repository

import com.example.cinemaxapp.core.domain.repository.MovieRepository
import com.example.cinemaxapp.core.model.FeaturedBanner
import com.example.cinemaxapp.core.model.HomeFeed
import com.example.cinemaxapp.core.model.Movie
import com.example.cinemaxapp.core.model.MovieCategory
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class MovieRepositoryImpl @Inject constructor() : MovieRepository {

    private val sampleBanners = listOf(
        FeaturedBanner(
            id = "b1",
            title = "Black Panther: Wakanda\nForever",
            releaseDateText = "On March 2, 2022",
            bannerImageUrl = "https://images.unsplash.com/photo-1534447677768-be436bb09401?w=800&auto=format&fit=crop&q=80",
        ),
        FeaturedBanner(
            id = "b2",
            title = "Avatar: The Way of\nWater",
            releaseDateText = "On December 16, 2022",
            bannerImageUrl = "https://images.unsplash.com/photo-1518709268805-4e9042af9f23?w=800&auto=format&fit=crop&q=80",
        ),
        FeaturedBanner(
            id = "b3",
            title = "The Batman",
            releaseDateText = "On March 4, 2022",
            bannerImageUrl = "https://images.unsplash.com/photo-1509198397868-475647b2a1e5?w=800&auto=format&fit=crop&q=80",
        ),
    )

    private val sampleCategories = listOf(
        MovieCategory("c1","All"),
        MovieCategory("c2", "Comedy"),
        MovieCategory("c3", "Animation"),
        MovieCategory("c4", "Documentary"),
        MovieCategory("c5", "Action"),
        MovieCategory("c6", "Drama"),
        MovieCategory("c7", "Sci-Fi"),
    )

    private val samplePopularMovies = listOf(
        Movie(
            id = "m1",
            title = "Spider-Man No..",
            posterUrl = "https://images.unsplash.com/photo-1635805737707-575885ab0820?w=600&auto=format&fit=crop&q=80",
            rating = 4.5,
            category = "Action",
        ),
        Movie(
            id = "m2",
            title = "Life of PI",
            posterUrl = "https://images.unsplash.com/photo-1511447333015-45b65e60f6d5?w=600&auto=format&fit=crop&q=80",
            rating = 4.5,
            category = "Action",
        ),
        Movie(
            id = "m3",
            title = "Riverdale",
            posterUrl = "https://images.unsplash.com/photo-1536440136628-849c177e76a1?w=600&auto=format&fit=crop&q=80",
            rating = 4.5,
            category = "Action",
        ),
        Movie(
            id = "m4",
            title = "Avengers: Endgame",
            posterUrl = "https://images.unsplash.com/photo-1568832359672-e36cf5d74f54?w=600&auto=format&fit=crop&q=80",
            rating = 4.8,
            category = "Action",
        ),
    )

    override fun getHomeFeed(): Flow<HomeFeed> = flow {
        emit(
            HomeFeed(
                userName = "Smith",
                userAvatarUrl = "https://images.unsplash.com/photo-1535713875002-d1d0cf377fde?w=200&auto=format&fit=crop&q=80",
                featuredBanners = sampleBanners,
                categories = sampleCategories,
                popularMovies = samplePopularMovies,
            )
        )
    }

    override suspend fun toggleWishlist(movieId: String): Boolean {
        return true
    }
}
