package com.example.cinemaxapp.core.domain.usecase

import com.example.cinemaxapp.core.domain.repository.MovieRepository
import com.example.cinemaxapp.core.model.Movie
import javax.inject.Inject


class GetPopularMoviesUseCase @Inject constructor(
    private val movieRepository: MovieRepository,
) {
    suspend operator fun invoke(genreId: String = ""): List<Movie> {
        return movieRepository.getPopularMovies(genreId = genreId)
    }
}
