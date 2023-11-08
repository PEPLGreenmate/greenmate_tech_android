package com.pepl.data.mapper

import com.pepl.data.api.model.PlantResponse
import com.pepl.model.Plant
import com.pepl.model.PlantStatus

internal fun PlantResponse.toData(): Plant =
    Plant(
        name = "",
        imageUrl = "",
        lastUpdate = 0L,
        gardenId = "",
        startPlantedDate = 0L,
        type = "",
        status = PlantStatus(0, 0, 0, 0)
    )