package com.pepl.domain.usecase

import com.pepl.data.repository.ChatRepository
import com.pepl.model.Chat
import javax.inject.Inject

class SendChatUseCase @Inject constructor(
    private val chatRepository: ChatRepository,
) {
    suspend operator fun invoke(chat: Chat): List<Chat> {
        return chatRepository.send(chat)
    }
}
