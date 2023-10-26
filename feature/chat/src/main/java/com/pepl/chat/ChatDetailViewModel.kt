package com.pepl.chat

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.pepl.domain.usecase.GetChatRoomsUseCase
import com.pepl.domain.usecase.GetChatsUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import javax.inject.Inject

@HiltViewModel
class ChatDetailViewModel @Inject constructor(
    getChatsUseCase: GetChatsUseCase,
) : ViewModel() {
    private val _errorFlow = MutableSharedFlow<Throwable>()
    val errorFlow: SharedFlow<Throwable> get() = _errorFlow

    val chatDetailUiState: StateFlow<ChatDetailUiState> = flow { emit(getChatsUseCase()) }
        .map { chats ->
            if (chats.isNotEmpty()) {
                ChatDetailUiState.Chat(chats)
            } else {
                ChatDetailUiState.Empty
            }
        }
        .catch { throwable ->
            _errorFlow.emit(throwable)
        }
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5_000),
            initialValue = ChatDetailUiState.Loading,
        )
}