package com.example.cinemaxapp.core.domain.repository

import com.example.cinemaxapp.core.model.HomeFeed
import com.example.cinemaxapp.core.model.Movie
import kotlinx.coroutines.flow.Flow

/**
 * Domain repository contract for movie data.
 */
interface MovieRepository {
    fun getHomeFeed(): Flow<HomeFeed>
    suspend fun toggleWishlist(movieId: String): Boolean
}
