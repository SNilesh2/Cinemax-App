package com.example.cinemaxapp.core.domain.usecase

import com.example.cinemaxapp.core.domain.repository.MovieRepository
import com.example.cinemaxapp.core.model.FeaturedBanner
import javax.inject.Inject

class GetFeaturedBannersUseCase @Inject constructor(
    private val movieRepository: MovieRepository,
) {

    suspend operator fun invoke(): List<FeaturedBanner> {
        return movieRepository.getFeaturedBanners()
    }
}
