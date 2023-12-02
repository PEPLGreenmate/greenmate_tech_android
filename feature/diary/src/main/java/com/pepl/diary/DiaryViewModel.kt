package com.pepl.diary

import android.util.Log
import androidx.lifecycle.ViewModel
import com.pepl.domain.usecase.GetDiaryUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.StateFlow
import javax.inject.Inject

@HiltViewModel
class DiaryViewModel @Inject constructor(
    getDiariesUseCase: GetDiaryUseCase,
) : ViewModel() {

    private val _errorFlow = MutableSharedFlow<Throwable>()
    val errorFlow: SharedFlow<Throwable> get() = _errorFlow

    val diaryUiState: StateFlow<DiaryUiState> get() = SharedDiaryUiState._diaryUiState

}


object SharedDiaryUiState {
    val _diaryUiState = MutableStateFlow<DiaryUiState>(DiaryUiState.Loading)

    fun setDiaryUiState(newState: DiaryUiState) {
        _diaryUiState.value = newState
        Log.d("DiaryUiState", "Value: {$newState}")
    }

}