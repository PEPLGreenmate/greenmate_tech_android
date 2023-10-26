package com.pepl.data.api.model

import kotlinx.serialization.Serializable

@Serializable
internal data class PlantResponse(
    val name: String,
    val startPlantedDate: Long,
    val type: String,
    val status: Int,
)
