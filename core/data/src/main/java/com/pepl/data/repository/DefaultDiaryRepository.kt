package com.pepl.data.repository

import android.util.Log
import com.pepl.data.api.AIApi
import com.pepl.data.mapper.request
import com.pepl.data.mapper.toData
import com.pepl.model.Diary
import javax.inject.Inject

class DefaultDiaryRepository @Inject constructor(
    private val greenmateAIApi: AIApi,
) : DiaryRepository {
    override suspend fun getDiary(diaryId: String): List<Diary> {
        return emptyList()
    }

    override suspend fun send(): List<Diary> {
        val m = greenmateAIApi.diarySend(request())
        Log.d("에러", "${m.body()}")

        return m.body()?.toData() ?: emptyList()
    }
}