package com.pepl.manage

sealed interface ManageUiState {

    object Loading : ManageUiState
    object Empty : ManageUiState

    data class Manage(
        val manages: List<String>,
    ) : ManageUiState
}