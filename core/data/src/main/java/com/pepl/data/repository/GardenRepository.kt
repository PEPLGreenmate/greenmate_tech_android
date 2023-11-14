package com.pepl.data.repository

import kotlinx.coroutines.flow.Flow

interface GardenRepository {

    suspend fun getGardens(): List<String>

    suspend fun getLastCheckedGarden(): Flow<String>
}