package com.example.cinemaxapp.core.domain.usecase

import com.example.cinemaxapp.core.domain.repository.SearchRepository
import com.example.cinemaxapp.core.model.SearchPerson
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetSearchPersonsUseCase @Inject constructor(
    private val searchRepository: SearchRepository,
){
    operator fun invoke(query: String): Flow<List<SearchPerson>>{
        return searchRepository.getSearchPersons(query)
    }
}