package com.pepl.dictionary

sealed interface DictionaryUiState {

    object Loading : DictionaryUiState
    object Empty : DictionaryUiState

    data class Dictionary(
        val dictionaries: List<String>,
    ) : DictionaryUiState
}