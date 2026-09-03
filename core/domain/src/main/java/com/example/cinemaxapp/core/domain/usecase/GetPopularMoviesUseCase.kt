package com.example.cinemaxapp.core.domain.usecase

import com.example.cinemaxapp.core.domain.repository.MovieRepository
import com.example.cinemaxapp.core.model.Movie
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject


class GetPopularMoviesUseCase @Inject constructor(
    private val movieRepository: MovieRepository,
) {
    operator fun invoke(genreId: String = ""): Flow<List<Movie>> {
        return movieRepository.getPopularMovies(genreId = genreId)
    }
}
