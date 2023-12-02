package com.pepl.data.repository

import com.pepl.model.Diary

interface DiaryRepository {

    suspend fun getDiary(diaryId: String): List<Diary>

    suspend fun send(): List<Diary>

}

