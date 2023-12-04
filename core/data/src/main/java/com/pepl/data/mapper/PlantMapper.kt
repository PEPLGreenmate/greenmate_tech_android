package com.pepl.data.mapper

import com.pepl.data.api.model.PlantResponse
import com.pepl.model.Plant
import com.pepl.model.PlantStatus
import com.pepl.util.getCurrentLongTime
import com.pepl.util.toLongTime
import com.pepl.util.toStartDateLongTime


internal fun PlantResponse.toData(): Plant = Plant(
    plantId = "",
    name = nickname,
    imageUrl = imageUrl,
    lastUpdate = measurementDateTime.toLongTime(),
    gardenId = "회사 책상 위",
    startPlantedDate = growthStartDate.toStartDateLongTime(),
    type = scientificName,
    status = PlantStatus(
        soil = recentSoilMoisture, recentSoilMoisture.toSoilMoistureState(),
        humidity = recentSoilMoisture, recentSoilMoisture.toSoilMoistureState(),
        light = recentIlluminance, recentIlluminance.toIlluminousState(),
        temp = recentTemperature, recentTemperature.toTempState()
    )
)

internal fun Int.toSoilMoistureState() = when {
    this < 40 -> "건조해요"
    this < 70 -> "적정"
    else -> "습해요"
}

internal fun Int.toIlluminousState() = when {
    this < 40 -> "어두워요"
    this < 70 -> "적정"
    else -> "너무 밝아요"
}

internal fun Int.toTempState() = when {
    this < 40 -> "추워요!"
    this < 70 -> "적정"
    else -> "더워요!"
}