package com.pepl.model

data class ChatRoom(
    val roomId: String,
    val plantImageUrl: String,
    val plantName: String,
    val isLacked: Boolean = true,
    val lackedAttribute: String = "water",
    val lastMessage: String,
    val lastSendTime: String,
    val isRead: Boolean,
) {
    companion object {
        fun createEmpty(): ChatRoom {
            return ChatRoom(
                roomId = "",
                plantImageUrl = "",
                plantName = "페플이",
                isLacked = true,
                lackedAttribute = "water",
                lastMessage = "나 목말라 물 좀 줘!",
                lastSendTime = "08:30",
                isRead = false,
            )
        }
    }
}
