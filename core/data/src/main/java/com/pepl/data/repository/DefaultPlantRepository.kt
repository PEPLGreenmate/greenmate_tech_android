package com.pepl.data.repository

import com.pepl.datastore.datasource.PlantDataSource
import javax.inject.Inject

class DefaultPlantRepository @Inject constructor(
    private val plantDataSource: PlantDataSource,
) : PlantRepository {
}