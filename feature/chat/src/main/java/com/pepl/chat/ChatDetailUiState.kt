package com.pepl.chat

sealed interface ChatDetailUiState {

    object Loading : ChatDetailUiState
    object Empty : ChatDetailUiState

    data class Chat(
        val chats: List<String>,
    ) : ChatDetailUiState
}