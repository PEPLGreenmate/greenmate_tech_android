package com.pepl.model

data class Plant(
    val name: String,
    val startPlantedDate: Long,
    val type: String,
    val status: PlantStatus,
)

data class PlantStatus(
    val soil: Int,
    val humidity: Int,
    val light: Int,
    val temp: Int,
)
