package com.example.cinemaxapp.feature.search

import androidx.paging.PagingData
import com.example.cinemaxapp.core.model.Movie
import com.example.cinemaxapp.core.model.SearchPerson
import kotlinx.coroutines.flow.Flow


sealed interface SearchUiState {


    data object Idle : SearchUiState


    data class Results(
        val query: String,
        val moviesFlow: Flow<PagingData<Movie>>,
        val persons: List<SearchPerson> = emptyList(),
    ) : SearchUiState


    data class NoResults(val query: String) : SearchUiState
}
