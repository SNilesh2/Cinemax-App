package com.example.cinemaxapp.feature.wishlist

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.cinemaxapp.core.domain.usecase.GetWishlistMoviesUseCase
import com.example.cinemaxapp.core.domain.usecase.ToggleWishlistUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class WishlistViewModel @Inject constructor(
    private val getWishlistMoviesUseCase: GetWishlistMoviesUseCase,
    private val toggleWishlistUseCase: ToggleWishlistUseCase,
) : ViewModel() {

    private val _uiState = MutableStateFlow<WishlistUiState>(WishlistUiState.Loading)


    val uiState: StateFlow<WishlistUiState> = _uiState.asStateFlow()

    init {
        observeWishlist()
    }


    private fun observeWishlist() {
        getWishlistMoviesUseCase()
            .onEach { movies ->
                _uiState.value = if (movies.isEmpty()) {
                    WishlistUiState.Empty
                } else {
                    WishlistUiState.Success(movies)
                }
            }
            .launchIn(viewModelScope)
    }


    fun onRemoveFromWishlist(movieId: Int) {
        viewModelScope.launch {
            toggleWishlistUseCase(movieId)
        }
    }
}
