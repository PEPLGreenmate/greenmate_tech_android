package com.pepl.domain.usecase

import com.pepl.data.repository.DiaryRepository
import com.pepl.model.Diary
import javax.inject.Inject

class GetDiaryUseCase @Inject constructor(
    private val diaryRepository: DiaryRepository,
) {
    suspend operator fun invoke(): List<Diary> {
        return diaryRepository.getDiary("")
    }
}
