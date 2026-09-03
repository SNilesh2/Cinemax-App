package com.example.cinemaxapp.core.domain.usecase

import com.example.cinemaxapp.core.domain.repository.MovieRepository
import com.example.cinemaxapp.core.model.FeaturedBanner
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetFeaturedBannersUseCase @Inject constructor(
    private val movieRepository: MovieRepository,
) {

    operator fun invoke(): Flow<List<FeaturedBanner>> {
        return movieRepository.getFeaturedBanners()
    }
}
