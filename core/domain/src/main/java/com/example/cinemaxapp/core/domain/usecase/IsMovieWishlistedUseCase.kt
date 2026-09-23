package com.example.cinemaxapp.core.domain.usecase

import com.example.cinemaxapp.core.domain.repository.MovieRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class IsMovieWishlistedUseCase @Inject constructor(
    private val movieRepository: MovieRepository,
) {
    operator fun invoke(movieId: Int) : Flow<Boolean>{
        return movieRepository.isMovieWishlisted(movieId)
    }
}