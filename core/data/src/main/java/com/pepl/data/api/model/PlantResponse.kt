package com.pepl.data.api.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class PlantResponse(
    @SerialName("lastUpdatedTime") val lastUpdatedTime: String,
    @SerialName("temperature") val temperature: Int,
    @SerialName("humidity") val humidity: Int,
    @SerialName("luminous") val luminous: Int,
    @SerialName("groundHumidity") val groundHumidity: Int,
    @SerialName("temperatureState") val temperatureState: String,
    @SerialName("humidityState") val humidityState: String,
    @SerialName("luminousState") val luminousState: String,
    @SerialName("groundHumidityState") val groundHumidityState:String
)
