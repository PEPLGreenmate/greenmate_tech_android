package com.pepl.data.repository

import com.pepl.model.Plant

interface PlantRepository {

    suspend fun getPlants(): List<Plant>

    suspend fun getPlantDetails(plantId: String)

}