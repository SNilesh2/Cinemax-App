package com.example.cinemaxapp.feature.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.cinemaxapp.core.domain.usecase.GetHomeFeedUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val getHomeFeedUseCase: GetHomeFeedUseCase,
) : ViewModel() {

    private val _uiState = MutableStateFlow<HomeUiState>(HomeUiState.Loading)
    val uiState: StateFlow<HomeUiState> = _uiState.asStateFlow()

    private val _selectedTab = MutableStateFlow(HomeBottomTab.HOME)
    val selectedTab: StateFlow<HomeBottomTab> = _selectedTab.asStateFlow()

    init {
        fetchHomeFeed()
    }

    fun fetchHomeFeed() {
        getHomeFeedUseCase()
            .onEach { feed ->
                val currentCategory = (_uiState.value as? HomeUiState.Success)?.selectedCategoryId ?: feed.categories.firstOrNull()?.id ?: "c1"
                _uiState.value = HomeUiState.Success(
                    homeFeed = feed,
                    selectedCategoryId = currentCategory
                )
            }
            .catch { throwable ->
                _uiState.value = HomeUiState.Error(throwable.message ?: "An unexpected error occurred")
            }
            .launchIn(viewModelScope)
    }

    fun onCategorySelected(categoryId: String) {
        val currentState = _uiState.value
        if (currentState is HomeUiState.Success) {
            _uiState.value = currentState.copy(selectedCategoryId = categoryId)
        }
    }

    fun onSearchQueryChanged(query: String) {
        val currentState = _uiState.value
        if (currentState is HomeUiState.Success) {
            _uiState.value = currentState.copy(searchQuery = query)
        }
    }

    fun onTabSelected(tab: HomeBottomTab) {
        _selectedTab.value = tab
    }
}
