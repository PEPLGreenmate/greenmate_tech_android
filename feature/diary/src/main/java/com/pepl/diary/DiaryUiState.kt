package com.pepl.diary

import android.util.Log
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

interface DiaryUiState {

    object Loading : DiaryUiState
    object Empty : DiaryUiState

    data class Diary(
        val diaries: List<com.pepl.model.Diary>,
    ) : DiaryUiState
}