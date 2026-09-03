package com.example.cinemaxapp.core.domain.usecase

import com.example.cinemaxapp.core.domain.repository.MovieRepository
import javax.inject.Inject


class SyncGenresUseCase @Inject constructor(
    private val movieRepository: MovieRepository,
) {
    suspend operator fun invoke() {
        movieRepository.syncGenres()
    }
}
