package com.pepl.diary

sealed interface DiaryUiState {

    object Loading : DiaryUiState
    object Empty : DiaryUiState

    data class Diary(
        val diaries: List<com.pepl.model.Diary>,
    ) : DiaryUiState
}