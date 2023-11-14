package com.pepl.domain.usecase

import com.pepl.data.repository.PlantRepository
import com.pepl.model.Plant
import javax.inject.Inject

class GetPlantsUseCase @Inject constructor(
    private val plantRepository: PlantRepository,
) {
    suspend operator fun invoke(gardenId: String): List<Plant> {
        if (gardenId.isEmpty()) return emptyList()
        return plantRepository.getPlants()
    }
}
