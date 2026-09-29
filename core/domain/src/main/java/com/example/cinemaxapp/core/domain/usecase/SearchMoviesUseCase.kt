package com.example.cinemaxapp.core.domain.usecase

import androidx.paging.PagingData
import com.example.cinemaxapp.core.domain.repository.SearchRepository
import com.example.cinemaxapp.core.model.Movie
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class SearchMoviesUseCase @Inject constructor(
    private val searchRepository: SearchRepository,
){
    operator fun invoke(query: String): Flow<PagingData<Movie>>{
        return searchRepository.searchMovies(query)
    }
}