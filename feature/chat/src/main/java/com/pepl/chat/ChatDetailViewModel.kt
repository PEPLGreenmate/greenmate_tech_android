package com.pepl.chat

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.pepl.domain.usecase.GetChatsUseCase
import com.pepl.domain.usecase.SendChatUseCase
import com.pepl.model.Chat
import com.pepl.util.getCurrentLongTime
import com.pepl.util.toChatDateString
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ChatDetailViewModel @Inject constructor(
    private val getChatsUseCase: GetChatsUseCase,
    private val sendChatUseCase: SendChatUseCase,
) : ViewModel() {
    private val _errorFlow = MutableSharedFlow<Throwable>()
    val errorFlow: SharedFlow<Throwable> get() = _errorFlow

    private val _chatDetailUiState =
        MutableStateFlow<ChatDetailUiState>(ChatDetailUiState.Loading)
    val chatDetailUiState: StateFlow<ChatDetailUiState> = _chatDetailUiState

    init {
        viewModelScope.launch {
            val chats = getChatsUseCase()

            _chatDetailUiState.value = ChatDetailUiState.Chat(chats)
        }
    }

    fun sendChat(message: String) {
        viewModelScope.launch {
            val sendChat = Chat(
                false,
                "",
                "user",
                getCurrentLongTime().toChatDateString(),
                message
            )
            val oldChats = if (_chatDetailUiState.value is ChatDetailUiState.Chat) {
                (_chatDetailUiState.value as ChatDetailUiState.Chat).chats + sendChat
            } else {
                listOf(sendChat)
            }

            _chatDetailUiState.value = ChatDetailUiState.Chat(
                oldChats
            )

            val chats = sendChatUseCase(
                sendChat
            )

            _chatDetailUiState.value = ChatDetailUiState.Chat(
                oldChats + chats
            )
        }
    }
}