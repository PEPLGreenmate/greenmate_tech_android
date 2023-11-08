package com.pepl.plant

import com.pepl.model.Plant

sealed interface PlantUiState {

    object Loading : PlantUiState
    object Empty : PlantUiState
    object GardenEmpty : PlantUiState

    data class Plants(
        val gardenId: String,
        val isGridMode: Boolean = false,
        val plants: List<Plant>,
    ) : PlantUiState
}