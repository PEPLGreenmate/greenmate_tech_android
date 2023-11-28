package com.pepl.data.api.model

import com.pepl.model.Chat
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class ChatRequest(
    @SerialName("input") val message: String,
    @SerialName("username") val username: String,
)

@Serializable
data class ChatResponse(
    @SerialName("chat_history") val chatHistory: String,
    @SerialName("input") val inputMessage: String,
    @SerialName("response") val responseMessage: String,
)
