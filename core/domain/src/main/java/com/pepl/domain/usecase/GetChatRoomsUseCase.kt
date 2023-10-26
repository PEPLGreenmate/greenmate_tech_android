package com.pepl.domain.usecase

import com.pepl.data.repository.ChatRepository
import com.pepl.model.ChatRoom
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetChatRoomsUseCase @Inject constructor(
    private val chatRepository: ChatRepository,
) {
    suspend operator fun invoke(): List<ChatRoom> {
        return chatRepository.getChatRooms()
    }
}