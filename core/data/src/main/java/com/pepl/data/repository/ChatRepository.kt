package com.pepl.data.repository

import com.pepl.model.Chat
import com.pepl.model.ChatRoom

interface ChatRepository {

    suspend fun getChatRooms(): List<ChatRoom>

    suspend fun getChats(roomId: String): List<Chat>

}