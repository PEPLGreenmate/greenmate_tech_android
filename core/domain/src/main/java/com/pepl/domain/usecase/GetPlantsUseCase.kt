package com.pepl.domain.usecase

import com.pepl.data.repository.PlantRepository
import javax.inject.Inject

class GetPlantsUseCase @Inject constructor(
    private val plantRepository: PlantRepository,
) {
    suspend operator fun invoke() {

    }
}
