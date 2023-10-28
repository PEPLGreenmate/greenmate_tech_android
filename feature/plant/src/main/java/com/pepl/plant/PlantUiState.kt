package com.pepl.plant

import com.pepl.model.Plant

sealed interface PlantUiState {

    object Loading : PlantUiState
    object Empty : PlantUiState

    data class Plants(
        val plants: List<Plant>,
    ) : PlantUiState
}