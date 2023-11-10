package com.pepl.diary

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.pepl.designsystem.theme.Typography
import kotlinx.coroutines.flow.collectLatest

@Composable
internal fun DiaryRoute(
    padding: PaddingValues,
    onShowErrorSnackBar: (throwable: Throwable?) -> Unit,
    viewModel: DiaryViewModel = hiltViewModel(),
) {
    val diaryUiState by viewModel.diaryUiState.collectAsStateWithLifecycle()

    LaunchedEffect(true) {
        viewModel.errorFlow.collectLatest { throwable -> onShowErrorSnackBar(throwable) }
    }

    DiaryScreen(
        padding = padding,
        diaryUiState = diaryUiState,
    )
}

@Composable
private fun DiaryScreen(
    padding: PaddingValues,
    diaryUiState: DiaryUiState,
) {
    val scrollState = rememberScrollState()

    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(
                start = 16.dp, end = 16.dp, top = 16.dp, bottom = 75.dp
            )
            .border(
                width = 1.dp,
                color = MaterialTheme.colorScheme.outline,
                shape = RoundedCornerShape(size = 28.dp)
            )
    ) {
        Column(
            Modifier
                .padding(padding)
                .padding(16.dp)
                .verticalScroll(scrollState),
            verticalArrangement = Arrangement.spacedBy(8.dp),
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 10.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    text = "다이어리",
                    style = Typography.dovemayoR17
                )
                Text(
                    text = "2023.10.29",
                    style = Typography.dovemayoR17
                )
            }

            Text(
                text = "제목: 뿌듯한 재회의 아침 ",
                style = Typography.dovemayoR19
            )
            Text(
                text = """내용: 오늘 아침, 여자친구와의 웃긴 이야기와 헬스장에서의 운동은 나에게 소중한 시간이었다. 여자친구와 함께 웃을 수 있는 그런 순간이 정말 행복하다고 느꼈다. 그리고 헬스장에서는 예전처럼 꾸준히 다니지 못했던 시간들을 회상하며, 오늘의 노력이 더욱 뿌듯하게 느껴졌다. 이런 경험을 통해 꾸준히 무언가를 이어나가는 것의 중요성을 다시금 느낄 수 있었다. 내일도 그 뿌듯함을 이어가기 위해, 실현 가능한 계획을 세워 나아가야겠다고 생각한다.
감정분석 결과:
기쁨: 40%
행복: 30%
뿌듯함: 20%
결심: 10%
키워드 정리:
여자친구
웃음
헬스장
꾸준함
뿌듯함
오늘의 하루도 정말 값진 하루였던 것 같아요. 앞으로도 이런 좋은 감정과 경험들이 계속되길 바랍니다! 화이팅! :)
""",
                style = Typography.dovemayoR12
            )
        }

    }

}