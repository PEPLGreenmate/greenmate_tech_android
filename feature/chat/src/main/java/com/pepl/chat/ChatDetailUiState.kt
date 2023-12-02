package com.pepl.chat

sealed interface ChatDetailUiState {

    object Loading : ChatDetailUiState
    object Empty : ChatDetailUiState

    data class Chat(
        val chats: List<com.pepl.model.Chat>,
    ) : ChatDetailUiState
}