package com.pepl.diary

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.pepl.domain.usecase.GetChatRoomsUseCase
import com.pepl.domain.usecase.GetChatsUseCase
import com.pepl.domain.usecase.GetDiaryUseCase
import com.pepl.domain.usecase.GetPlantsUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.stateIn
import javax.inject.Inject

@HiltViewModel
class DiaryViewModel @Inject constructor(
    getDiariesUseCase: GetDiaryUseCase,
) : ViewModel() {

    private val _errorFlow = MutableSharedFlow<Throwable>()
    val errorFlow: SharedFlow<Throwable> get() = _errorFlow

    val diaryUiState: StateFlow<DiaryUiState> get() = SharedDiaryUiState._diaryUiState

//    val diaryUiState: StateFlow<DiaryUiState> = flow { emit(getDiariesUseCase()) }
//        .map { diaries ->
//            if (diaries.isNotEmpty()) {
//                DiaryUiState.Diary(diaries)
//            } else {
//                DiaryUiState.Empty
//            }
//        }
//        .catch { throwable ->
//            _errorFlow.emit(throwable)
//        }
//        .stateIn(
//            scope = viewModelScope,
//            started = SharingStarted.WhileSubscribed(5_000),
//            initialValue = DiaryUiState.Loading,
//        )
}


object SharedDiaryUiState {
    val _diaryUiState = MutableStateFlow<DiaryUiState>(DiaryUiState.Loading)

    fun setDiaryUiState(newState: DiaryUiState) {
        _diaryUiState.value = newState
        Log.d("DiaryUiState", "Value: {$newState}")
    }

}