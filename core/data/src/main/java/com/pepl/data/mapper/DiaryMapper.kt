package com.pepl.data.mapper

import android.text.format.DateUtils
import com.pepl.data.api.model.ChatRequest
import com.pepl.data.api.model.ChatResponse
import com.pepl.data.api.model.DiaryRequest
import com.pepl.data.api.model.DiaryResponse
import com.pepl.model.Chat
import com.pepl.model.Diary
import com.pepl.util.getCurrentLongTime
import com.pepl.util.toChatDateString

internal fun DiaryResponse.toData(userName: String): List<Diary> {
    val lists = mutableListOf<Diary>()

    lists.add(
        Diary(
            date = getCurrentLongTime().toChatDateString(),
            imageUrl = "https://images.unsplash.com/photo-1512428813834-c702c7702b78?auto=format&fit=crop&q=80&w=1587&ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8fA%3D%3D",
            title = getCurrentLongTime().toChatDateString(),
            content = ""
        )
    )

    return lists.toList()
}

internal fun request(): DiaryRequest {
    return DiaryRequest(
        username = "minho"
    )
}