package com.pepl.dictionary

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.pepl.domain.usecase.GetFriendUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.stateIn
import javax.inject.Inject

@HiltViewModel
class DictionaryViewModel @Inject constructor(
    getFriendsUseCase: GetFriendUseCase,
) : ViewModel() {

    private val _errorFlow = MutableSharedFlow<Throwable>()
    val errorFlow: SharedFlow<Throwable> get() = _errorFlow

    val friendsUiState: StateFlow<DictionaryUiState> = flow { emit(getFriendsUseCase()) }
        .map { friends ->
//            if (friends.isNotEmpty()) {
//                FriendsUiState.Friends(friends)
//            } else {
//                FriendsUiState.Empty
//            }
            DictionaryUiState.Empty
        }
        .catch { throwable ->
            _errorFlow.emit(throwable)
        }
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5_000),
            initialValue = DictionaryUiState.Loading,
        )
}