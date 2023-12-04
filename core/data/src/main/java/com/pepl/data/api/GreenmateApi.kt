package com.pepl.data.api

import com.pepl.data.api.model.PlantResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Header

interface GreenmateApi {

    @GET("/api/plants/4")
    suspend fun requestPlants(
        @Header("Authorization") Authorization: String
    ): Response<PlantResponse>
}

