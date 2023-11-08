package com.pepl.data.repository

import com.pepl.data.api.GreenmateApi
import com.pepl.datastore.datasource.PlantDataSource
import com.pepl.model.Plant
import com.pepl.model.PlantStatus
import javax.inject.Inject

class DefaultPlantRepository @Inject constructor(
    private val greenmateApi: GreenmateApi,
) : PlantRepository {
    override suspend fun getPlants(): List<Plant> {
        return listOf(
            Plant(
                "페플이",
                "https://images.unsplash.com/photo-1512428813834-c702c7702b78?auto=format&fit=crop&q=80&w=1587&ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8fA%3D%3D",
                "",
                0L,
                "몬스테라",
                0L,
                PlantStatus(
                    30,
                    50,
                    150,
                    34
                )
            ),
            Plant(
                "플래그먼트",
                "https://images.unsplash.com/photo-1512428813834-c702c7702b78?auto=format&fit=crop&q=80&w=1587&ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8fA%3D%3D",
                "",
                0L,
                "바질",
                0L,
                PlantStatus(
                    30,
                    10,
                    48,
                    34
                )
            ),
            Plant(
                "선인장",
                "https://images.unsplash.com/photo-1512428813834-c702c7702b78?auto=format&fit=crop&q=80&w=1587&ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8fA%3D%3D",
                "",
                0L,
                "스투키",
                0L,
                PlantStatus(
                    30,
                    70,
                    200,
                    26
                )
            ),
            Plant(
                "그린메이트",
                "https://images.unsplash.com/photo-1512428813834-c702c7702b78?auto=format&fit=crop&q=80&w=1587&ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8fA%3D%3D",
                "",
                0L,
                "야자나무",
                0L,
                PlantStatus(
                    35,
                    53,
                    150,
                    34
                )
            )
        )
    }

    override suspend fun getPlantDetails(plantId: String) {
        TODO("Not yet implemented")
    }
}