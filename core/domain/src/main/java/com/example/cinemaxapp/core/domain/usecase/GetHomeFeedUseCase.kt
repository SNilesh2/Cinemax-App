package com.example.cinemaxapp.core.domain.usecase

import com.example.cinemaxapp.core.domain.repository.MovieRepository
import com.example.cinemaxapp.core.model.HomeFeed
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

/**
 * Use case to fetch home screen feed content.
 */
class GetHomeFeedUseCase @Inject constructor(
    private val movieRepository: MovieRepository,
) {
    operator fun invoke(): Flow<HomeFeed> {
        return movieRepository.getHomeFeed()
    }
}
