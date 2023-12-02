package com.pepl.diary

interface DiaryUiState {

    object Loading : DiaryUiState
    object Empty : DiaryUiState

    data class Diary(
        val diaries: List<com.pepl.model.Diary>,
    ) : DiaryUiState
}