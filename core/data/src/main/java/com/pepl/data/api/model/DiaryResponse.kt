package com.pepl.data.api.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable


@Serializable
data class DiaryRequest(
    @SerialName("username") val username: String,
)

@Serializable
data class DiaryResponse(
    @SerialName("diary_result") val diaryResult: String,
    @SerialName("emotion_result") val emotionResult: String
)