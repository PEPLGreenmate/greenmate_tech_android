package com.pepl.home

import com.pepl.model.Plant

sealed interface FriendsUiState {

    object Loading : FriendsUiState
    object Empty : FriendsUiState

    data class Friends(
        val friends: List<Plant>,
    ) : FriendsUiState
}