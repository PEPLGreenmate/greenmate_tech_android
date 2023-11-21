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
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.Constraints
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.zIndex
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.pepl.designsystem.theme.mayo
import kotlinx.coroutines.flow.collectLatest

@Composable
@NonRestartableComposable
fun Spacer(modifier: Modifier) {
    Layout({}, measurePolicy = SpacerMeasurePolicy, modifier = modifier)
}

private object SpacerMeasurePolicy : MeasurePolicy {

    override fun MeasureScope.measure(
        measurables: List<Measurable>,
        constraints: Constraints
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

    DiaryScreen()
}


@Composable
private fun DiaryScreen() {
    val scrollState = rememberScrollState()

    Box( //전체 화면
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
                    scaleX = -1f // Set scaleX to -1 for horizontal flip
                )
                .zIndex(1f)

        )
        Column( // 헤더 / 바디
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight(),
            horizontalAlignment = Alignment.CenterHorizontally
        )
        {

            Column( //헤더
                modifier = Modifier
                    .padding(top = 50.dp, bottom = 5.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    text = "기분이 좋은",
                    style = TextStyle(
                        fontSize = 12.sp,
                        lineHeight = 12.sp,
                        //fontFamily = FontFamily(Font(R.font.pretendard)),
                        fontWeight = FontWeight(500),
                        color = Color(0xFF94959D),
                    )
                )
                Spacer(modifier = Modifier.height(5.dp))
                Text(
                    modifier = Modifier
                        .width(100.dp)
                        .padding(bottom = 10.dp),
                    text = "23년 9월 30일 토요일 ",
                    style = TextStyle(
                        fontSize = 17.37.sp,
                        lineHeight = 17.37.sp,
                        fontFamily = mayo,
                        fontWeight = FontWeight(400),
                        color = Color(0xFF000000),
                        textAlign = TextAlign.Center,
                    )
                )
            }
            Box( //바디(박스)
                modifier = Modifier
                    .shadow(
                        elevation = 30.dp,
                        spotColor = Color(0x40000000),
                        ambientColor = Color(0x40000000)
                    )
                    //.width(330.dp)
                    .fillMaxWidth(0.85f)
                    .fillMaxHeight(0.85f)
                    .background(color = Color(0xFFFFFFFF), shape = RoundedCornerShape(size = 20.dp))
                    .verticalScroll(scrollState)
            ) {
                Box( //이미지 박스
                    modifier= Modifier
                        .padding(top = 480.dp, start = 15.dp)
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
                            //.rotate(-1F)
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

                Column(
                    modifier = Modifier
                        .padding(16.dp),
                    verticalArrangement = Arrangement.spacedBy(8.dp),
                ) {
                    Text(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(
                                bottom = 10.dp,
                                top = 13.dp
                            ),
                        text = "행운목 입양한 날!",
                        style = TextStyle(
                            fontSize = 19.3.sp,
                            lineHeight = 19.3.sp,
                            fontWeight = FontWeight(400),
                            color = Color(0xFF414147),
                            fontFamily = mayo,
                            textAlign = TextAlign.Center,
                        )
                    )
                    Box( //회색선
                        modifier = Modifier
                            .padding(start = 45.dp)
                            .height(0.96515.dp)
                            .width(215.22787.dp)
                            .background(
                                color = Color(0xFFD1D2D9),
                                shape = RoundedCornerShape(size = 14.dp)
                            ) ,
                        contentAlignment = Alignment.Center,
                    )
                    {
                    }
                    Spacer(modifier = Modifier.height(10.dp))
                    Text(
                        text = """오늘 정말 기분 좋았어. 
아침에 일어나서 행운목 봤더니 정말 예쁘게 햇빛에 비쳤어. 그래서 기분이 좋아서 하루가 기대됐어.

오늘은 회사 갈 때 길에 꽃 향기가 진동했어. 이번 주에 프로젝트 미팅이 있는데 조금 긴장돼서 행운목 마음으로 자신감 가지고 출근했어.

미팅은 생각보다 완전 잘 진행됐어. 동료들이랑 아이디어 나누고 협력하면서 우리 프로젝트 발전시키는 게 정말 재밌었어. 상사도 아이디어 칭찬해줘서 기분이 좋았어.

점심 때는 동료들이랑 식사하면서 웃음 가득한 대화 나눴어. 직장 생활이 친구랑 놀러오는 것 같아.
오후에는 업무 끝내고 집에 오는 길에 행운목 아래서 쉬었어. 자연과 나의 작은 나무 덕분에 마음이 편안해져.

                             집 와서 행운목에 물 주고 잎들
                             닦아줬어. 이 나무랑 함께하는 건              
                              정말 행운 같아서 감사해.

                               오늘은 행운목과 함께한 날로 
                               정말 기분 좋아. 내일도 더 열심
                                히 일하자!
""",
                        style = TextStyle(
                            fontSize = 14.sp,
                            lineHeight = 22.sp,
                            letterSpacing = 0.3.sp,
                            fontFamily = mayo,
                            fontWeight = FontWeight(400),
                            color = Color(0xFF6D6D6D),

                            )
                    )
                }

            }

        }
    }




}