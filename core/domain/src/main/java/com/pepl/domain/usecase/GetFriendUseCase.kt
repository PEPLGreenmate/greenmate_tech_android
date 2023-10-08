package com.pepl.domain.usecase

import com.pepl.data.repository.PlantRepository
import javax.inject.Inject

class GetFriendUseCase @Inject constructor(
    private val plantRepository: PlantRepository,
) {

    suspend operator fun invoke() {
    }
}