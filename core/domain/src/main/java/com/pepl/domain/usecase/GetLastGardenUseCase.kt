package com.pepl.domain.usecase

import com.pepl.data.repository.ChatRepository
import com.pepl.data.repository.GardenRepository
import com.pepl.model.Chat
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetLastGardenUseCase @Inject constructor(
    private val gardenRepository: GardenRepository,
) {
    suspend operator fun invoke(): Flow<String> {
        return gardenRepository.getLastCheckedGarden()
    }
}