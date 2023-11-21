package com.pepl.datastore.datasource

import kotlinx.coroutines.flow.Flow

interface GardenDataSource {

    val lastGardenId: Flow<String>
    suspend fun updateLastGardenId(gardenId: String)
}