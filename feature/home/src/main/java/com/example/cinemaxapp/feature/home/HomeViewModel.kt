package com.example.cinemaxapp.feature.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.cinemaxapp.core.domain.usecase.GetFeaturedBannersUseCase
import com.example.cinemaxapp.core.domain.usecase.GetMovieCategoriesUseCase
import com.example.cinemaxapp.core.domain.usecase.GetPopularMoviesUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.async
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class HomeViewModel @Inject constructor(
    private val getFeaturedBannersUseCase: GetFeaturedBannersUseCase,
    private val getMovieCategoriesUseCase: GetMovieCategoriesUseCase,
    private val getPopularMoviesUseCase: GetPopularMoviesUseCase,
) : ViewModel() {

    // The single source of truth for the HomeScreen's visual state.
    // MutableStateFlow: can be written to (only inside this ViewModel)
    // StateFlow: read-only view exposed to the UI
    private val _uiState = MutableStateFlow<HomeUiState>(HomeUiState.Loading)
    val uiState: StateFlow<HomeUiState> = _uiState.asStateFlow()

    private val _selectedTab = MutableStateFlow(HomeBottomTab.HOME)
    val selectedTab: StateFlow<HomeBottomTab> = _selectedTab.asStateFlow()

    // `init` runs automatically when Hilt creates the ViewModel (when HomeScreen appears).
    init {
        loadHomeData()
    }

    /**
     * Loads all HomeScreen data by invoking the three use cases.
     *
     * CONCURRENCY STRATEGY — why async/await instead of sequential calls:
     *
     *   Sequential (slower):
     *     val banners    = getFeaturedBannersUseCase()   // waits ~500ms
     *     val movies     = getPopularMoviesUseCase()     // waits another ~500ms
     *     Total: ~1000ms
     *
     *   Concurrent with async (faster):
     *     val bannersJob = async { getFeaturedBannersUseCase() }  // starts immediately
     *     val moviesJob  = async { getPopularMoviesUseCase() }    // starts immediately
     *     bannersJob.await() + moviesJob.await()                  // waits for both
     *     Total: ~500ms (both run at the same time)
     *
     * getMovieCategories() is not async because it's instant (no network call).
     */
    fun loadHomeData() {
        _uiState.value = HomeUiState.Loading
        viewModelScope.launch {
            try {
                // Start the two network calls concurrently
                val bannersDeferred = async { getFeaturedBannersUseCase() }
                val categoriesDeferred = async { getMovieCategoriesUseCase() }  // now hits TMDB Genre API
                val moviesDeferred  = async { getPopularMoviesUseCase(genreId = "") }

                // Categories are instant (hardcoded) — no async needed
                val categories = categoriesDeferred.await()

                // .await() suspends until each async block finishes,
                // then builds the Success state with all three results.
                _uiState.value = HomeUiState.Success(
                    featuredBanners    = bannersDeferred.await(),
                    categories         = categories,
                    popularMovies      = moviesDeferred.await(),
                    selectedCategoryId = categories.firstOrNull()?.id ?: "",
                )
            } catch (e: Exception) {
                // If either async block throws (e.g., SocketTimeoutException),
                // the catch block here handles it and shows the error state.
                _uiState.value = HomeUiState.Error(
                    message = e.message ?: "An unexpected error occurred"
                )
            }
        }
    }

    // ─── User Interaction Handlers ────────────────────────────────────────────────

    fun onCategorySelected(categoryId: String) {
        val current = _uiState.value
        if (current is HomeUiState.Success) {
            // copy() creates a new object with only selectedCategoryId changed.
            // All other fields remain the same — no re-fetch needed.
            _uiState.value = current.copy(selectedCategoryId = categoryId)


            viewModelScope.launch {
                try {
                    val movies = getPopularMoviesUseCase(genreId = categoryId)

                    // Use the very latest state — it may have changed while we were fetching
                    // (e.g. the user tapped another category). Only update popularMovies.
                    val latest = _uiState.value
                    if (latest is HomeUiState.Success) {
                        _uiState.value = latest.copy(popularMovies = movies)
                    }
                } catch (e: Exception) {
                    // If the genre-specific fetch fails, keep the existing movie list visible.
                    // We don't flip to HomeUiState.Error here because that would wipe the
                    // entire screen (banners, categories etc.) just because one genre fetch failed.
                    // The user can try another category or pull-to-refresh.
                    android.util.Log.w(
                        "HomeViewModel",
                        "Failed to fetch movies for genre $categoryId: ${e.message}"
                    )
                }
            }
        }
    }

    fun onSearchQueryChanged(query: String) {
        val current = _uiState.value
        if (current is HomeUiState.Success) {
            _uiState.value = current.copy(searchQuery = query)
        }
    }

    fun onTabSelected(tab: HomeBottomTab) {
        _selectedTab.value = tab
    }
}
