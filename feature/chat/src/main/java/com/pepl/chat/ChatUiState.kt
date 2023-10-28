package com.pepl.chat


sealed interface ChatUiState {

    object Loading : ChatUiState
    object Empty : ChatUiState

    data class ChatRoom(
        val chatRooms: List<com.pepl.model.ChatRoom>,
    ) : ChatUiState
}