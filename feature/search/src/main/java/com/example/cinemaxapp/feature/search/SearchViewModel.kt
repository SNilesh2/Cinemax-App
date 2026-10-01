package com.example.cinemaxapp.feature.search

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.cachedIn
import com.example.cinemaxapp.core.domain.usecase.GetSearchPersonsUseCase
import com.example.cinemaxapp.core.domain.usecase.SearchMoviesUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.update
import javax.inject.Inject


@HiltViewModel
class SearchViewModel @Inject constructor(
    private val searchMoviesUseCase: SearchMoviesUseCase,
    private val getSearchPersonsUseCase: GetSearchPersonsUseCase,
) : ViewModel() {

    // ── Live text in the search bar ──────────────────────────
    private val _inputQuery = MutableStateFlow("")
    val inputQuery: StateFlow<String> = _inputQuery.asStateFlow()

    // ── Query that was actually submitted ────────────────────
    private val _submittedQuery = MutableStateFlow("")

    // ── UI state exposed to the screen ───────────────────────
    private val _uiState = MutableStateFlow<SearchUiState>(SearchUiState.Idle)
    val uiState: StateFlow<SearchUiState> = _uiState.asStateFlow()

    init {
        observeSubmittedQuery()
    }


    fun onInputQueryChange(query: String) {
        _inputQuery.update { query }
    }


    fun onSearch(query: String) {
        val trimmed = query.trim()
        if (trimmed.isNotBlank() && trimmed != _submittedQuery.value) {
            _submittedQuery.update { trimmed }
            // Also sync the input field to the trimmed value
            _inputQuery.update { trimmed }
        }
    }


    fun onClear() {
        _inputQuery.update { "" }
        _submittedQuery.update { "" }
        _uiState.update { SearchUiState.Idle }
    }


    @OptIn(ExperimentalCoroutinesApi::class)
    private fun observeSubmittedQuery() {
        _submittedQuery
            .onEach { query ->
                if (query.isBlank()) return@onEach

                // Build paginated movies Flow for this query (cached so rotation doesn't re-fetch)
                val moviesFlow = searchMoviesUseCase(query).cachedIn(viewModelScope)

                // Observe persons for this query (Room Flow — re-emits when mediator writes)
                getSearchPersonsUseCase(query)
                    .onEach { persons ->
                        _uiState.update {
                            SearchUiState.Results(
                                query      = query,
                                moviesFlow = moviesFlow,
                                persons    = persons,
                            )
                        }
                    }
                    .launchIn(viewModelScope)
            }
            .launchIn(viewModelScope)
    }
}
