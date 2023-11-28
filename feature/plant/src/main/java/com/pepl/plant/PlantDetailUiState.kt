package com.pepl.plant

import com.pepl.model.Plant

sealed interface PlantDetailUiState {
    object Loading : PlantDetailUiState
    object Empty : PlantDetailUiState
    data class Plant(
        val plant: Plant,
    ) : PlantDetailUiState
}