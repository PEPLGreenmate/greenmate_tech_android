package com.pepl.data.api

import com.pepl.data.api.model.ChatRequest
import com.pepl.data.api.model.ChatResponse
import com.pepl.data.api.model.DiaryRequest
import com.pepl.data.api.model.DiaryResponse
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.POST

interface AIApi {

    @POST("/chat")
    suspend fun send(
        @Body request: ChatRequest,
    ): Response<ChatResponse>

    @POST("/diary")
    suspend fun diarySend(
        @Body request: DiaryRequest,
    ): Response<DiaryResponse>


}