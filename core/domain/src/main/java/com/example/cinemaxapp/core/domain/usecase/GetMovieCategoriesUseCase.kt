package com.example.cinemaxapp.core.domain.usecase

import com.example.cinemaxapp.core.domain.repository.MovieRepository
import com.example.cinemaxapp.core.model.MovieCategory
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

/**
 * Use case: Provide the list of available movie categories for the Home screen filter bar.
 *
 * Single Responsibility: This use case has ONE job — provide movie categories.
 * It does not know about banners or popular movies.
 *
 * NOTE: This is NOT a suspend function because categories are hardcoded in the repository —
 * there is no network call or I/O. No suspension is needed.
 *
 * Called by: HomeViewModel
 * Calls: MovieRepository.getMovieCategories()
 */
class GetMovieCategoriesUseCase @Inject constructor(
    private val movieRepository: MovieRepository,
) {
    operator fun invoke(): Flow<List<MovieCategory>> {
        return movieRepository.getMovieCategories()
    }
}
