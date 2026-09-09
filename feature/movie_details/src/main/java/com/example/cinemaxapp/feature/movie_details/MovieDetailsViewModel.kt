package com.example.cinemaxapp.feature.movie_details

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.cinemaxapp.core.domain.usecase.GetMovieDetailsUseCase
import com.example.cinemaxapp.core.domain.usecase.SyncMovieDetailsUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class MovieDetailsViewModel @Inject constructor(
    savedStateHandle: SavedStateHandle,
    private val getMovieDetailsUseCase: GetMovieDetailsUseCase,
    private val syncMovieDetailsUseCase: SyncMovieDetailsUseCase,
) : ViewModel() {


    private val movieId: Int = checkNotNull(savedStateHandle["movieId"]) {
        "movieId is required in SavedStateHandle for MovieDetailsViewModel"
    }

    private val _uiState = MutableStateFlow<MovieDetailsUiState>(MovieDetailsUiState.Loading)

    val uiState: StateFlow<MovieDetailsUiState> = _uiState.asStateFlow()

    init {
        observeMovieDetails()
        triggerSync()
    }


    private fun observeMovieDetails() {
        getMovieDetailsUseCase(movieId)
            .onEach { movieDetails ->
                _uiState.update { current ->
                    when {
                        movieDetails != null -> MovieDetailsUiState.Success(movieDetails)
                        current is MovieDetailsUiState.Loading -> MovieDetailsUiState.Loading
                        else -> current   // keep existing Success state; don't flash back to Loading
                    }
                }
            }
            .launchIn(viewModelScope)
    }


    private fun triggerSync() {
        viewModelScope.launch {
            syncMovieDetailsUseCase(movieId)
        }
    }
}
