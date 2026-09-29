package com.example.cinemaxapp.core.domain.repository

import androidx.paging.PagingData
import com.example.cinemaxapp.core.model.Movie
import com.example.cinemaxapp.core.model.SearchPerson
import kotlinx.coroutines.flow.Flow

interface SearchRepository {
    fun searchMovies(query: String): Flow<PagingData<Movie>>

    fun getSearchPersons(query: String): Flow<List<SearchPerson>>
}