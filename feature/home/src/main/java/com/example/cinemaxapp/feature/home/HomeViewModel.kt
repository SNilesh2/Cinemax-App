package com.example.cinemaxapp.feature.home

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.cinemaxapp.core.domain.repository.MovieRepository
import com.example.cinemaxapp.core.domain.usecase.GetFeaturedBannersUseCase
import com.example.cinemaxapp.core.domain.usecase.GetMovieCategoriesUseCase
import com.example.cinemaxapp.core.domain.usecase.GetPopularMoviesUseCase
import com.example.cinemaxapp.core.domain.usecase.SyncGenresUseCase
import com.example.cinemaxapp.core.domain.usecase.SyncMoviesForGenreUseCase
import com.example.cinemaxapp.core.domain.usecase.SyncNowPlayingMoviesUseCase
import com.example.cinemaxapp.core.model.MovieCategory
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
class HomeViewModel @Inject constructor(
    private val getFeaturedBannersUseCase: GetFeaturedBannersUseCase,
    private val getMovieCategoriesUseCase: GetMovieCategoriesUseCase,
    private val getPopularMoviesUseCase: GetPopularMoviesUseCase,
    private val syncNowPlayingMoviesUseCase: SyncNowPlayingMoviesUseCase,
    private val syncGenresUseCase: SyncGenresUseCase,
    private val syncMoviesForGenreUseCase: SyncMoviesForGenreUseCase,
) : ViewModel() {

    // The single source of truth for the HomeScreen's visual state.
    private val _uiState = MutableStateFlow<HomeUiState>(HomeUiState.Loading)
    val uiState: StateFlow<HomeUiState> = _uiState.asStateFlow()

    private val _selectedTab = MutableStateFlow(HomeBottomTab.HOME)
    val selectedTab: StateFlow<HomeBottomTab> = _selectedTab.asStateFlow()


    private var currentGenreId: String = "0"

    init {
        observeFeaturedBanners()
        observeCategories()
        observeMoviesForGenre("0")   // "0" = "All" on initial load

        // Start background sync immediately — fetch fresh data from TMDB and save to Room.
        // When each sync completes, the Flows above automatically re-emit with fresh data.
        triggerInitialSync()
    }

    // ═══════════════════════════════════════════════════════════
    // FLOW OBSERVATION — Read from Room database (source of truth)
    // ═══════════════════════════════════════════════════════════


    private fun observeFeaturedBanners() {
        getFeaturedBannersUseCase()
            .onEach { banners ->
                _uiState.update { current ->
                    when (current) {
                        is HomeUiState.Loading ->
                            // First emission: create the Success state with banners
                            HomeUiState.Success(featuredBanners = banners)
                        is HomeUiState.Success ->
                            // Subsequent emissions: update just the banners field
                            current.copy(featuredBanners = banners)
                        is HomeUiState.Error ->
                            // Recovery: if we previously showed an error, now show data
                            HomeUiState.Success(featuredBanners = banners)
                    }
                }
            }
            .launchIn(viewModelScope)   // Runs until ViewModel is destroyed
    }


    private fun observeCategories() {
        getMovieCategoriesUseCase()
            .onEach { categories ->
                _uiState.update { current ->
                    val isFirstTime = current !is HomeUiState.Success
                    val currentSelected = (current as? HomeUiState.Success)?.selectedCategoryId ?: ""

                    when (current) {
                        is HomeUiState.Loading ->
                            HomeUiState.Success(
                                categories = categories,
                                // Auto-select "All" (first category) on first load
                                selectedCategoryId = categories.firstOrNull()?.id ?: "",
                            )
                        is HomeUiState.Success ->
                            current.copy(
                                categories = categories,
                                // Keep existing selection, unless it's gone from the new list
                                selectedCategoryId = if (currentSelected.isEmpty() && isFirstTime)
                                    categories.firstOrNull()?.id ?: "" else currentSelected,
                            )
                        is HomeUiState.Error ->
                            HomeUiState.Success(
                                categories = categories,
                                selectedCategoryId = categories.firstOrNull()?.id ?: "",
                            )
                    }
                }
            }
            .launchIn(viewModelScope)
    }


    private var moviesJob: kotlinx.coroutines.Job? = null

    private fun observeMoviesForGenre(genreId: String) {
        moviesJob?.cancel()  // Stop observing the previous genre
        moviesJob = getPopularMoviesUseCase(genreId = genreId)
            .onEach { movies ->
                _uiState.update { current ->
                    when (current) {
                        is HomeUiState.Loading ->
                            HomeUiState.Success(popularMovies = movies)
                        is HomeUiState.Success ->
                            current.copy(popularMovies = movies)
                        is HomeUiState.Error ->
                            HomeUiState.Success(popularMovies = movies)
                    }
                }
            }
            .launchIn(viewModelScope)
    }

    // ═══════════════════════════════════════════════════════════
    // BACKGROUND SYNC — Fetch from TMDB, save to Room
    // These never affect the UI directly — they write to Room,
    // which triggers Flow re-emissions, which update the UI.
    // ═══════════════════════════════════════════════════════════


    private fun triggerInitialSync() {
        viewModelScope.launch {
            // These can run sequentially — genres must be loaded before we know valid genre IDs
            syncNowPlayingMoviesUseCase()
            syncGenresUseCase()
            Log.d("HomeViewModel", "Initial sync complete")
        }
    }

    // ═══════════════════════════════════════════════════════════
    // USER INTERACTION HANDLERS
    // ═══════════════════════════════════════════════════════════


    fun onCategorySelected(categoryId: String) {
        if (categoryId == currentGenreId) return  // No-op if already selected
        currentGenreId = categoryId

        // STEP 1: Highlight the chip immediately
        val current = _uiState.value
        if (current is HomeUiState.Success) {
            _uiState.value = current.copy(selectedCategoryId = categoryId)
        }

        // STEP 2a: Switch the movies Flow to the new genre
        observeMoviesForGenre(categoryId)

        // STEP 2b: Trigger a background sync for this genre's movies
        // Skip for "All" (genreId="0") — already synced in triggerInitialSync
        if (categoryId != "0") {
            viewModelScope.launch {
                syncMoviesForGenreUseCase(categoryId)
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
