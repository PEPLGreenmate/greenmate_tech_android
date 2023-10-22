package com.pepl.setting

sealed interface SettingUiState {

    object Loading : SettingUiState
    object Empty : SettingUiState

    data class Setting(
        val settings: List<String>,
    ) : SettingUiState
}