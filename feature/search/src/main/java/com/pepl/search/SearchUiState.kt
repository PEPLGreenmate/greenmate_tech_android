package com.pepl.search

sealed interface SearchUiState {

    object Loading : SearchUiState
    object Empty : SearchUiState

    data class SearchPlants(
        val plants: List<String>,
    ) : SearchUiState
}