package com.pepl.chat

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.pepl.diary.DiaryUiState
import com.pepl.diary.SharedDiaryUiState
import com.pepl.domain.usecase.GetChatRoomsUseCase
import com.pepl.domain.usecase.SendDiaryUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ChatViewModel @Inject constructor(
    getChatRoomsUserCase: GetChatRoomsUseCase,
    private val sendDiaryUseCase: SendDiaryUseCase
) : ViewModel() {

    private val _errorFlow = MutableSharedFlow<Throwable>()
    val errorFlow: SharedFlow<Throwable> get() = _errorFlow

    val chatUiState: StateFlow<ChatUiState> = flow { emit(getChatRoomsUserCase()) }
        .map { chats ->
            if (chats.isNotEmpty()) {
                ChatUiState.ChatRoom(chats)
            } else {
                ChatUiState.Empty
            }
        }
        .catch { throwable ->
            _errorFlow.emit(throwable)
        }
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5_000),
            initialValue = ChatUiState.Loading,
        )

    fun sendDiary() {
        viewModelScope.launch {
            try {
                val result = sendDiaryUseCase()
                SharedDiaryUiState.setDiaryUiState(DiaryUiState.Diary(diaries = result))

                Log.d("DiaryUiState", "Value: $result")
            } catch (e: Exception) {
                _errorFlow.emit(e)
            }
        }
    }
}