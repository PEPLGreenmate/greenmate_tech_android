package com.pepl.data.mapper

import android.text.format.DateUtils
import com.pepl.data.api.model.ChatRequest
import com.pepl.data.api.model.ChatResponse
import com.pepl.model.Chat
import com.pepl.util.getCurrentLongTime
import com.pepl.util.toChatDateString

internal fun ChatResponse.toData(userName: String): List<Chat> {
    return listOf(
        Chat(
            isPlantChat = true,
            imageUrl = "https://images.unsplash.com/photo-1512428813834-c702c7702b78?auto=format&fit=crop&q=80&w=1587&ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8fA%3D%3D",
            name = "",
            sendTime = getCurrentLongTime().toChatDateString(),
            message = responseMessage
        )
    )
}

internal fun Chat.toRequest(): ChatRequest {
    return ChatRequest(
        message = message,
        username = "minho"
    )
}