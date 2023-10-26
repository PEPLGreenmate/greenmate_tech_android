package com.pepl.plant

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.layout.systemBarsPadding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.ColorPainter
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.pepl.designsystem.component.NetworkImage
import com.pepl.designsystem.theme.Gray
import com.pepl.designsystem.theme.Typography
import kotlinx.coroutines.flow.collectLatest

@Composable
internal fun PlantRoute(
    padding: PaddingValues,
    onShowErrorSnackBar: (throwable: Throwable?) -> Unit,
    viewModel: PlantViewModel = hiltViewModel(),
) {
    val plantUiState by viewModel.plantUiState.collectAsStateWithLifecycle()

    LaunchedEffect(true) {
        viewModel.errorFlow.collectLatest { throwable -> onShowErrorSnackBar(throwable) }
    }

    PlantScreen(
        padding = padding,
        plantUiState = plantUiState,
    )
}

@Composable
private fun PlantScreen(
    padding: PaddingValues,
    plantUiState: PlantUiState,
) {
    val scrollState = rememberScrollState()
    Column(
        Modifier
            .padding(padding)
            .statusBarsPadding()
            .padding(horizontal = 8.dp, vertical = 16.dp)
            .verticalScroll(scrollState),
        verticalArrangement = Arrangement.spacedBy(8.dp),
    ) {
        Row(
            modifier = Modifier.fillMaxSize(),
            horizontalArrangement = Arrangement.Center
        ) {
            Text(
                text = "식물관리",
                style = Typography.titleLarge
            )
        }
        Spacer(modifier = Modifier.height(10.dp))


        Column(
            modifier = Modifier.fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(10.dp)
        ) {
            NetworkImage(
                imageUrl = "https://unsplash.com/ko/%EC%82%AC%EC%A7%84/%EA%B0%88%EC%83%89-%EB%82%98%EB%AC%B4-%EA%BD%83%EB%B3%91%EC%97%90-%EB%85%B9%EC%83%89-%EC%8B%9D%EB%AC%BC-Ebwp2-6BG8E",
                placeholder = ColorPainter(Gray),
                modifier = Modifier
                    .size(80.dp)
                    .clip(CircleShape)
                    .background(Gray)
                    .clickable(onClick = {})
            )
            Text(
                text = "페풀이",
                style = Typography.bodyLarge
            )
            Text(
                text = "3시간 전 기준",
                style = Typography.bodyMedium,
                color = Gray
            )
            Spacer(modifier = Modifier.height(10.dp))
            Text(
                text = "현재 온도: (좋음) 23 적정 온도 : 20C ~ 30C",
                style = Typography.bodyMedium,
            )
            Text(
                text = "현재 습도: (좋음) 50 적정 습도 : 50%~70%",
                style = Typography.bodyMedium,
            )
            Text(
                text = "조도: (나쁨) 300 적정 조도 : 6000lux",
                style = Typography.bodyMedium,
            )
            Text(
                text = "토양수분: (건조) 30% 적정 토양수분: 60% ~ 70%",
                style = Typography.bodyMedium,
            )
        }
    }
}