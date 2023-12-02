package com.pepl.data.repository

import com.pepl.data.api.GreenmateApi
import com.pepl.datastore.datasource.GardenDataSource
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class DefaultGardenRepository @Inject constructor(
    private val greenmateApi: GreenmateApi,
    private val gardenDataSource: GardenDataSource,
) : GardenRepository {

    private val lastGardenId: Flow<String> = gardenDataSource.lastGardenId
    override suspend fun getGardens(): List<String> {
        TODO("Not yet implemented")
    }

    override suspend fun getLastCheckedGarden(): Flow<String> {
        return lastGardenId
    }
}