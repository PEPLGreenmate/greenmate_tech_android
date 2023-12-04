package com.pepl.data.api.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class PlantResponse(
    @SerialName("nickname") val nickname: String,
    @SerialName("scientificName") val scientificName: String,
    @SerialName("growthStartDate") val growthStartDate: String,
    @SerialName("measurementDateTime") val measurementDateTime: String,
    @SerialName("recentTemperature") val recentTemperature: Int,
    @SerialName("recentIlluminance") val recentIlluminance: Int,
    @SerialName("recentSoilMoisture") val recentSoilMoisture: Int,
    @SerialName("imageUrl") val imageUrl: String,
)