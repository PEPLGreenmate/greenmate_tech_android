package com.pepl.model

data class Plant(
    val plantId: String,
    val name: String,
    val imageUrl: String,
    val gardenId: String,
    val startPlantedDate: Long,
    val type: String,
    val lastUpdate: Long,
    val status: PlantStatus,
)

data class PlantStatus(
    val soil: Int,
    val soilState: String,
    val humidity: Int,
    val humidityState: String,
    val light: Int,
    val lightState: String,
    val temp: Int,
    val tempState: String,
)