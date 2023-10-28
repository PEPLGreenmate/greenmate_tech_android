package com.pepl.domain.usecase

import com.pepl.data.repository.ChatRepository
import com.pepl.model.Chat
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetChatsUseCase @Inject constructor(
    private val chatRepository: ChatRepository,
) {
    suspend operator fun invoke(): List<Chat> {
        return chatRepository.getChats("")
    }
}
