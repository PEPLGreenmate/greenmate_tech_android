package com.pepl.domain.usecase

import com.pepl.data.repository.GardenRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetLastGardenUseCase @Inject constructor(
    private val gardenRepository: GardenRepository,
) {
    suspend operator fun invoke(): Flow<String> {
        return gardenRepository.getLastCheckedGarden()
    }
}