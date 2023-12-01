package com.pepl.data.repository

import android.util.Log
import com.pepl.data.api.AIApi
import com.pepl.data.api.GreenmateApi
import com.pepl.data.api.model.ChatResponse
import com.pepl.data.di.ApiModule
import com.pepl.data.mapper.toData
import com.pepl.data.mapper.toRequest
import com.pepl.model.Chat
import com.pepl.model.ChatRoom
import javax.inject.Inject

class DefaultChatRepository @Inject constructor(
    private val greenmateAIApi: AIApi,
) : ChatRepository {
    override suspend fun getChatRooms(): List<ChatRoom> {
        return listOf(
            ChatRoom(
                "",
                "https://images.unsplash.com/photo-1512428813834-c702c7702b78?auto=format&fit=crop&q=80&w=1587&ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8fA%3D%3D",
                "페플이",
                true,
                "water",
                "나 목말라, 물이 필요해!!",
                "08:30",
                false
            ),
            ChatRoom(
                "",
                "https://images.unsplash.com/photo-1512428813834-c702c7702b78?auto=format&fit=crop&q=80&w=1587&ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8fA%3D%3D",
                "그린메이트",
                false,
                "water",
                "오늘 하루 화이팅이에요!",
                "08:25",
                true
            ),
            ChatRoom(
                "",
                "https://images.unsplash.com/photo-1512428813834-c702c7702b78?auto=format&fit=crop&q=80&w=1587&ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8fA%3D%3D",
                "선인장",
                true,
                "water",
                "나 목말라, 물이 필요해!!",
                "08:30",
                false
            ),
            ChatRoom(
                "",
                "https://images.unsplash.com/photo-1512428813834-c702c7702b78?auto=format&fit=crop&q=80&w=1587&ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8fA%3D%3D",
                "그린조아",
                true,
                "water",
                "오늘 하루 화이팅이에요!",
                "08:30",
                false
            ),
            ChatRoom(
                "",
                "https://images.unsplash.com/photo-1512428813834-c702c7702b78?auto=format&fit=crop&q=80&w=1587&ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8fA%3D%3D",
                "플래그먼트",
                true,
                "water",
                "나 목말라, 물이 필요해!!",
                "08:10",
                true
            )
        )
    }

    override suspend fun getChats(roomId: String): List<Chat> {
        return emptyList()
    }

    override suspend fun send(chat: Chat): List<Chat> {
        val r = greenmateAIApi.send(chat.toRequest()).body()
        Log.d("ChatSend","$r")
        return greenmateAIApi.send(chat.toRequest()).body()?.toData("minho") ?: emptyList()
    }
}