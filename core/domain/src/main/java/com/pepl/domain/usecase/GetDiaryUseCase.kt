package com.pepl.domain.usecase

import com.pepl.data.repository.ChatRepository
import com.pepl.data.repository.DiaryRepository
import com.pepl.model.Chat
import com.pepl.model.Diary
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetDiaryUseCase @Inject constructor(
    private val diaryRepository: DiaryRepository,
) {
    suspend operator fun invoke(): List<Diary> {
        return diaryRepository.getDiary("")
    }
}
