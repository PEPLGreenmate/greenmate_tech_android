package com.pepl.data.api

import com.pepl.data.api.model.PlantResponse
import retrofit2.http.GET

interface GreenmateApi {

    @GET("/api/plant")
    suspend fun requestPlants(): PlantResponse
}