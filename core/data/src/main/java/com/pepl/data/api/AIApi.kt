package com.pepl.data.api

import com.pepl.data.api.model.ChatRequest
import com.pepl.data.api.model.ChatResponse
import retrofit2.http.Body
import retrofit2.http.POST
import retrofit2.http.Query

interface AIApi {

    @POST("/chat")
    suspend fun send(
        @Body request: ChatRequest,
    ): ChatResponse

}