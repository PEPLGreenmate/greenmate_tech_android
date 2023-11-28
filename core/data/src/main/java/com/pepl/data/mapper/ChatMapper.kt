package com.pepl.data.mapper

import android.text.format.DateUtils
import com.pepl.data.api.model.ChatRequest
import com.pepl.data.api.model.ChatResponse
import com.pepl.model.Chat
import com.pepl.util.getCurrentLongTime
import com.pepl.util.toChatDateString

internal fun ChatResponse.toData(userName: String): List<Chat> {
    val lists = mutableListOf<Chat>()

    var startTime = "08:10"
    var plantName = ""
    chatHistory.split("\n").forEachIndexed { index, it ->
        val chat = it.split(":")

        lists.add(
            if (chat[0] == userName) {
                Chat(
                    isPlantChat = false,
                    imageUrl = "https://images.unsplash.com/photo-1512428813834-c702c7702b78?auto=format&fit=crop&q=80&w=1587&ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8fA%3D%3D",
                    name = chat[0],
                    sendTime = startTime + index,
                    message = chat[1]
                )
            } else {
                plantName = chat[0]
                Chat(
                    isPlantChat = true,
                    imageUrl = "",
                    name = chat[0],
                    sendTime = startTime + index,
                    message = chat[1]
                )
            }
        )
    }
    lists.add(
        Chat(
            false,
            "",
            name = userName,
            sendTime = getCurrentLongTime().toChatDateString(),
            message = inputMessage
        )
    )
    lists.add(
        Chat(
            isPlantChat = true,
            imageUrl = "https://images.unsplash.com/photo-1512428813834-c702c7702b78?auto=format&fit=crop&q=80&w=1587&ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8fA%3D%3D",
            name = plantName,
            sendTime = getCurrentLongTime().toChatDateString(),
            message = responseMessage
        )
    )

    return lists.toList()
}

internal fun Chat.toRequest(): ChatRequest {
    return ChatRequest(
        message = message,
        username = "user"
    )
}