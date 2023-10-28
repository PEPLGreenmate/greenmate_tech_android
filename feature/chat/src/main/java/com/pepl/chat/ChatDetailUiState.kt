package com.pepl.chat

import com.pepl.model.Chat

sealed interface ChatDetailUiState {

    object Loading : ChatDetailUiState
    object Empty : ChatDetailUiState

    data class Chat(
        val chats: List<com.pepl.model.Chat>,
    ) : ChatDetailUiState
}