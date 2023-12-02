package com.pepl.diary

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.NonRestartableComposable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.layout.Layout
import androidx.compose.ui.layout.Measurable
import androidx.compose.ui.layout.MeasurePolicy
import androidx.compose.ui.layout.MeasureResult
import androidx.compose.ui.layout.MeasureScope
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.Constraints
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.zIndex
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.pepl.designsystem.theme.Typography
import com.pepl.ui.LoadingScreen
import eu.wewox.textflow.TextFlow
import eu.wewox.textflow.TextFlowObstacleAlignment
import kotlinx.coroutines.flow.collectLatest


@Composable
@NonRestartableComposable
fun Spacer(modifier: Modifier) {
    Layout({}, measurePolicy = SpacerMeasurePolicy, modifier = modifier)
}

private object SpacerMeasurePolicy : MeasurePolicy {

    override fun MeasureScope.measure(
        measurables: List<Measurable>,
        constraints: Constraints,
    ): MeasureResult {
        return with(constraints) {
            val width = if (hasFixedWidth) maxWidth else 0
            val height = if (hasFixedHeight) maxHeight else 0
            layout(width, height) {}
        }
    }
}

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
        diaryUiState = diaryUiState
    )
}


@Composable
private fun DiaryScreen(
    diaryUiState: DiaryUiState,
    modifier: Modifier = Modifier
) {
    val diaryUiState = diaryUiState
    val scrollState = rememberScrollState()


    Box(
        modifier = Modifier
            .fillMaxWidth()
            .fillMaxHeight(),
        contentAlignment = Alignment.Center
    )
    {
        Image(
            painter = painterResource(id = com.pepl.greenmate.core.designsystem.R.drawable.main_logo),
            contentDescription = null,
            modifier = Modifier
                .padding(
                    top = 62.dp,
                    start = 45.dp
                )
                .size(
                    width = 93.38603.dp,
                    height = 121.23346.dp
                )
                .align(Alignment.TopStart)

        )
        Image(
            painter = painterResource(id = com.pepl.greenmate.core.designsystem.R.drawable.main_logo),
            contentDescription = null,

            modifier = Modifier
                .align(Alignment.BottomEnd)
                .fillMaxHeight(0.3f)
                .padding(
                    start = 310.dp
                )
                .size(
                    width = 116.dp,
                    height = 146.dp
                )
                .graphicsLayer(
                    scaleX = -1f
                )
                .zIndex(1f)

        )
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight(),
            horizontalAlignment = Alignment.CenterHorizontally
        )
        {

            Column(
                modifier = Modifier
                    .padding(top = 50.dp, bottom = 5.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                when (diaryUiState) {
                    is DiaryUiState.Loading -> {
                       LoadingScreen()
                    }

                    is DiaryUiState.Empty -> {
                        Text(
                            text = "",
                            style = Typography.dovemayoR19,
                            textAlign = TextAlign.Center,
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(16.dp)
                        )
                    }

                    is DiaryUiState.Diary -> {
                        Text(
                            text = "기분이 좋은",
                            style = Typography.pretendardM12.copy(
                                color = Color(0xFF94959D),
                            )
                        )
                        Spacer(modifier = Modifier.height(5.dp))
                        Text(
                            modifier = Modifier
                                .width(115.dp)
                                .padding(bottom = 10.dp),
                            text = diaryUiState.diaries.first().date,
                            style = Typography.dovemayoR18,
                            textAlign = TextAlign.Center
                        )
                    }
                }
            }
            Box(
                modifier = Modifier
                    .shadow(
                        elevation = 30.dp,
                        spotColor = Color(0x40000000),
                        ambientColor = Color(0x40000000)
                    )
                    .fillMaxWidth(0.85f)
                    .fillMaxHeight(0.85f)
                    .background(color = Color(0xFFFFFFFF), shape = RoundedCornerShape(size = 20.dp))
                    .verticalScroll(scrollState)
            ) {
                Column(
                    modifier = Modifier
                        .padding(16.dp),
                    verticalArrangement = Arrangement.spacedBy(8.dp),
                ) {
                    when (diaryUiState) {
                        is DiaryUiState.Loading -> {
                            LoadingScreen()
                        }

                        is DiaryUiState.Empty -> {
                            Text(
                                text = "",
                                style = Typography.dovemayoR19,
                                textAlign = TextAlign.Center,
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(16.dp)
                            )
                        }

                        is DiaryUiState.Diary -> {
                            Text(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(
                                        bottom = 10.dp,
                                        top = 13.dp
                                    ),
                                text = diaryUiState.diaries.first().title,
                                style = Typography.dovemayoR19,
                                textAlign = TextAlign.Center
                            )
                            Box(
                                //회색선
                                modifier = Modifier
                                    .padding(start = 45.dp)
                                    .height(0.96515.dp)
                                    .width(215.22787.dp)
                                    .background(
                                        color = Color(0xFFD1D2D9),
                                        shape = RoundedCornerShape(size = 14.dp)
                                    ),
                                contentAlignment = Alignment.Center,
                            )
                            {
                            }
                            Spacer(modifier = Modifier.height(10.dp))
                        }
                    }
                    when (diaryUiState) {
                        is DiaryUiState.Loading -> {
                            Text(
                                text = "일기가 없습니다",
                                style = Typography.dovemayoR19,
                                textAlign = TextAlign.Center,
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(16.dp)
                            )
                        }
                        is DiaryUiState.Empty -> {
                            Text(
                                text = "일기가 없습니다",
                                style = Typography.dovemayoR19,
                                textAlign = TextAlign.Center,
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(16.dp)
                            )
                        }
                        is DiaryUiState.Diary -> {
                            TextFlow(
                                text = diaryUiState.diaries.first().content,
                                style = Typography.dovemayoR16.copy(
                                    lineHeight = 22.sp,
                                    letterSpacing = 0.3.sp,
                                    fontWeight = FontWeight(400)
                                ),
                                modifier = Modifier
                                    .padding(15.dp),
                                obstacleAlignment = TextFlowObstacleAlignment.TopEnd
                            ) {
                                Box(
                                    modifier = Modifier
                                        .width(116.36361.dp)
                                        .height(169.42961.dp)
                                        .rotate(-4F)
                                        .background(
                                            color = Color(0x61BDC8C3),
                                            shape = RoundedCornerShape(size = 7.23861.dp)
                                        )
                                )
                                {
                                    Box(
                                        modifier = Modifier
                                            .width(108.08009.dp)
                                            .height(135.87109.dp)
                                            .padding(
                                                top = 6.5.dp,
                                                start = 7.dp
                                            )
                                    )
                                    {
                                        Image(
                                            painter = painterResource(id = com.pepl.greenmate.core.designsystem.R.drawable.plant_diary2),
                                            contentDescription = "image description",
                                            contentScale = ContentScale.Crop
                                        )
                                    }
                                }
                            }
                        }
                    }
                }

            }

        }
    }
}