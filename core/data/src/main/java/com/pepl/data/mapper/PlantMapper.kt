package com.pepl.data.mapper

import com.pepl.data.api.model.PlantResponse
import com.pepl.model.Plant
import com.pepl.model.PlantStatus
import com.pepl.util.getCurrentLongTime
import com.pepl.util.toLongTime
import kotlinx.serialization.SerialName

internal fun PlantResponse.toData(): Plant = Plant(
    name = "그린메이트",
    imageUrl = "https://images.unsplash.com/photo-1512428813834-c702c7702b78?auto=format&fit=crop&q=80&w=1587&ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8fA%3D%3D",
    lastUpdate = lastUpdatedTime.toLongTime(),
    gardenId = "회사 책상 위",
    startPlantedDate = getCurrentLongTime(),
    type = "몬스테라",
    status = PlantStatus(
        soil = groundHumidity, groundHumidityState.toLocalState(),
        humidity = humidity, humidityState.toLocalState(),
        light = luminous, luminousState.toLocalState(),
        temp = temperature, temperatureState.toLocalState()
    )
)

internal fun String.toLocalState() = when (this) {
    "COLD" -> "너무 추워요!"
    "NORMAL" -> "적정"
    "HOT" -> "너무 더워요!"
    "DRY" -> "건조해요!"
    "WET" -> "습해요!"
    "DARK" -> "어두워요!"
    "BRIGHT" -> "너무 밝아요!"
    else -> ""
}

internal fun String.toRequestState() = when (this) {
    "너무 추워요!" -> "COLD"
    "적정" -> "NORMAL"
    "너무 더워요!" -> "HOT"
    "건조해요!" -> "DRY"
    "습해요!" -> "WET"
    "어두워요!" -> "DARK"
    "너무 밝아요!" -> "BRIGHT"
    else -> ""
}