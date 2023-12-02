package com.pepl.data.repository

import com.pepl.data.api.GreenmateApi
import com.pepl.data.mapper.toData
import com.pepl.model.Plant
import javax.inject.Inject

class DefaultPlantRepository @Inject constructor(
    private val greenmateApi: GreenmateApi,
) : PlantRepository {
    override suspend fun getPlants(): List<Plant> {
        return listOf(greenmateApi.requestPlants().toData())
    }

    override suspend fun getPlantDetails(plantId: String) {
        TODO("Not yet implemented")
    }
}