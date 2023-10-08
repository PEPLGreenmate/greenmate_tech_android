package com.pepl.chat

sealed interface ChatUiState {

    object Loading : ChatUiState
    object Empty : ChatUiState

    data class Chat(
        val chats: List<String>,
    ) : ChatUiState
}