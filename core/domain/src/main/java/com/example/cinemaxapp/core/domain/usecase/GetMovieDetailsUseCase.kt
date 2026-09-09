package com.example.cinemaxapp.core.domain.usecase

import com.example.cinemaxapp.core.domain.repository.MovieRepository
import com.example.cinemaxapp.core.model.MovieDetails
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject


class GetMovieDetailsUseCase @Inject constructor(
    private val movieRepository: MovieRepository,
) {
    operator fun invoke(movieId: Int): Flow<MovieDetails?> {
        return movieRepository.getMovieDetails(movieId)
    }
}
