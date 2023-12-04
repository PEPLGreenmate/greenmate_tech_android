package com.pepl.data.repository

import com.pepl.data.api.GreenmateApi
import com.pepl.data.mapper.toData
import com.pepl.model.Plant
import javax.inject.Inject

class DefaultPlantRepository @Inject constructor(
    private val greenmateApi: GreenmateApi,
) : PlantRepository {
    override suspend fun getPlants(): List<Plant> {
        val res = greenmateApi.requestPlants(
            "Bearer eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJpc3MiOiJncmVlbm1hdGUiLCJleHAiOjE3MDI4Njg3MDksInN1YiI6IjEifQ.DdlD4RENmwglBUp3LtAJXbFbyVwGtMZD5Cjn67eTqT8"
        )
        val body = res.body()
        return if (body != null) {
            listOf(body.toData())
        } else {
            emptyList()
        }
    }

    override suspend fun getPlantDetails(plantId: String) {
        TODO("Not yet implemented")
    }
}