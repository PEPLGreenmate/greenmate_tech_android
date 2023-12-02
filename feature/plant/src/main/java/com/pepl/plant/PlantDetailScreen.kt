package com.pepl.plant

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Divider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.ColorPainter
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.pepl.designsystem.component.NetworkImage
import com.pepl.designsystem.theme.DarkGray
import com.pepl.designsystem.theme.Gray
import com.pepl.designsystem.theme.GreenMateTheme
import com.pepl.designsystem.theme.MainGreen
import com.pepl.designsystem.theme.Typography
import com.pepl.designsystem.theme.White
import com.pepl.model.Plant
import com.pepl.model.PlantStatus
import kotlinx.coroutines.flow.collectLatest

@Composable
internal fun PlantDetailRoute(
    padding: PaddingValues,
    plantId: String,
    onBackClick: () -> Unit,
    onShowErrorSnackBar: (throwable: Throwable?) -> Unit,
    viewModel: PlantDetailViewModel = hiltViewModel(),
) {
    val plantDetailUiState by viewModel.plantDetailUiState.collectAsStateWithLifecycle()

    LaunchedEffect(true) {
        viewModel.errorFlow.collectLatest { throwable -> onShowErrorSnackBar(throwable) }
    }

    PlantDetailScreen(
        padding = padding,
        plantDetailUiState = plantDetailUiState,
        plantId = plantId,
        onBackClick = onBackClick
    )
}

@Composable
internal fun PlantDetailScreen(
    padding: PaddingValues,
    plantDetailUiState: PlantDetailUiState,
    plantId: String,
    onBackClick: () -> Unit,
) {

    Box(
        modifier = Modifier.fillMaxSize()
    ) {
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
        ) {
            item {
                PlantDetailHeader()
                PlantDetailContent()
            }
        }
    }
}

@Composable
fun PlantDetailHeader() {
    val contentSize = (LocalConfiguration.current.screenWidthDp.dp) / 5
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .wrapContentHeight()
            .background(MainGreen)
    ) {
        NetworkImage(
            imageUrl = "https://images.unsplash.com/photo-1512428813834-c702c7702b78?auto=format&fit=crop&q=80&w=1587&ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8fA%3D%3D",
            modifier = Modifier
                .width(contentSize * 3)
                .fillMaxHeight(0.5f)
                .clip(
                    CircleShape.copy(
                        topStart = CornerSize(0.dp),
                        bottomStart = CornerSize(0.dp)
                    )
                ),
            placeholder = ColorPainter(Gray),
            contentDescription = "식물 사진"
        )
        Column(
            horizontalAlignment = Alignment.End,
            verticalArrangement = Arrangement.SpaceAround,
            modifier = Modifier
                .fillMaxHeight(0.5f)
                .padding(end = 24.dp)
                .align(Alignment.CenterEnd)
        ) {
            PlantNameAndType()

            PlantLocationAndDate()

        }
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(30.dp)
                .align(Alignment.BottomCenter)
                .clip(
                    RoundedCornerShape(
                        topStart = 22.dp,
                        topEnd = 22.dp,
                    )
                )
                .background(White)
        )

    }

}

@Composable
fun PlantNameAndType() {
    Column(
        horizontalAlignment = Alignment.End
    ) {
        Text(
            text = "금어화",
            style = Typography.suitL15,
            color = White
        )
        Spacer(
            modifier = Modifier.height(12.dp)
        )
        Text(
            text = "플래그먼트",
            style = Typography.nanumB24,
            color = White
        )
    }

}

@Composable
fun PlantLocationAndDate() {
    Column(
        horizontalAlignment = Alignment.End
    ) {
        Text(
            text = "회사 책상 위",
            style = Typography.nanumB15,
            color = White
        )
        Spacer(
            modifier = Modifier.height(3.dp)
        )
        Text(
            text = "에서",
            style = Typography.suitB15,
            color = DarkGray
        )
        Spacer(
            modifier = Modifier.height(22.dp)
        )
        Text(
            text = "함께한 지",
            style = Typography.suitB15,
            color = DarkGray
        )
        Spacer(
            modifier = Modifier.height(16.dp)
        )
        Text(
            text = "32일",
            style = Typography.dovemayoR28,
            color = White
        )
    }
}


@Composable
fun PlantDetailContent() {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 24.dp)
    ) {
        Column() {
            Column() {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 40.dp),
                    horizontalArrangement = Arrangement.SpaceAround
                ) {
                    PlantDetailContentButton("식물상태")
                    PlantDetailContentButton("타임라인")
                    PlantDetailContentButton("모습기록")
                }
                Spacer(
                    modifier = Modifier.height(12.dp)
                )
                Divider(
                    color = Gray,
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(1.dp)
                )
                Spacer(
                    modifier = Modifier.height(22.dp)
                )
                PlantStatusChart(
                    plant = Plant(
                        plantId = "",
                        name = "그린메이트",
                        "",
                        "회사 책상 위",
                        0L,
                        "몬스테라",
                        0L,
                        PlantStatus(40, "적정", 30, "적정", 140, "너무 밝아요", 30, "너무 더워요!")
                    )
                )
                Spacer(
                    modifier = Modifier.height(55.dp)
                )
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceAround

                ) {
                    Text(
                        text = "기록하기",
                        style = Typography.nanumB11,
                        color = Gray
                    )
                    Text(
                        text = "도감 이동",
                        style = Typography.nanumB11,
                        color = Gray
                    )
                    Text(
                        text = "오늘의 날씨",
                        style = Typography.nanumB11,
                        color = Gray
                    )
                }
                Spacer(
                    modifier = Modifier.height(60.dp)
                )
            }
            Column() {
                TimelineContent()
            }

            Column() {
                Spacer(
                    modifier = Modifier.height(51.dp)
                )
                PhotoHistoryContent()
            }
        }
    }
}

@Composable
fun TimelineContent() {
    val items = listOf("9월 11일", "9월 12일", "9월 14일")

    Column() {
        TimelineHeader()
        Spacer(
            modifier = Modifier.height(20.dp)
        )
        items.forEach {
            TimelineGroupItem(it)
        }
    }

}

@Composable
fun TimelineHeader() {
    Column {
        Row(
            modifier = Modifier
                .fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            PlantDetailContentButton("타임라인")

            Row() {
                PlantDetailButton(text = "도감보기") {

                }
                Spacer(modifier = Modifier.width(15.dp))
                PlantDetailButton(text = "상태체크") {

                }
            }

        }
        Divider(
            modifier = Modifier
                .height(1.dp)
                .background(Gray)
        )
    }
}

@Composable
fun TimelineGroupItem(date: String) {

    val manages = listOf("물주기", "환기하기", "영양제")
    Column(
        modifier = Modifier
            .wrapContentHeight()
            .padding(bottom = 12.dp)
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.Bottom
        ) {
            Text(
                text = date,
                style = Typography.dovemayoR14
            )
            Divider(
                modifier = Modifier
                    .height(1.dp)
                    .background(Gray)
            )
        }
        Spacer(
            modifier = Modifier.height(10.dp)
        )
        manages.forEach {
            TimelineItem(it)
        }
    }
}

@Composable
fun TimelineItem(management: String) {
    Row(
        modifier = Modifier
            .height(IntrinsicSize.Min),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Box(
            modifier = Modifier
                .fillMaxHeight(1F),
            contentAlignment = Alignment.Center
        ) {
            Divider(
                modifier = Modifier
                    .width(1.dp)
                    .fillMaxHeight(),
                color = Gray
            )
            Box(
                modifier = Modifier
                    .background(color = MainGreen, shape = CircleShape)
                    .size(9.dp)
                    .align(Alignment.Center)
            )
        }
        Spacer(modifier = Modifier.width(16.dp))
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 12.dp)
                .shadow(
                    elevation = 30.dp,
                    ambientColor = Color(0x40000000),
                    spotColor = Color(0x40000000),
                    shape = RoundedCornerShape(10.dp)
                )
                .background(White),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Image(
                painter = painterResource(id = com.pepl.greenmate.core.designsystem.R.drawable.ic_water),
                contentDescription = "관리 목록 아이콘",
                modifier = Modifier.padding(start = 15.dp)
            )
            Text(
                text = management,
                modifier = Modifier
                    .padding(15.dp)
                    .weight(1F),
                style = Typography.nanumB11
            )
        }

    }
}

@Composable
fun PhotoHistoryContent() {
    val images = listOf(
        "https://images.unsplash.com/photo-1512428813834-c702c7702b78?auto=format&fit=crop&q=80&w=1587&ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8fA%3D%3D",
        "https://images.unsplash.com/photo-1512428813834-c702c7702b78?auto=format&fit=crop&q=80&w=1587&ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8fA%3D%3D",
        "https://images.unsplash.com/photo-1512428813834-c702c7702b78?auto=format&fit=crop&q=80&w=1587&ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8fA%3D%3D",
        "https://images.unsplash.com/photo-1512428813834-c702c7702b78?auto=format&fit=crop&q=80&w=1587&ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8fA%3D%3D",
    )
    Column() {
        PhotoHistoryHeader()
        Spacer(
            modifier = Modifier.height(12.dp)
        )
        LazyRow(
            horizontalArrangement = Arrangement.spacedBy(10.dp)
        ) {
            items(images) {
                NetworkImage(
                    imageUrl = it,
                    modifier = Modifier.width(100.dp),
                )
            }
        }
        Spacer(
            modifier = Modifier.height(20.dp)
        )
    }
}

@Composable
fun PhotoHistoryHeader() {
    Column {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 10.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            PlantDetailContentButton("모습 기록")
        }
        Divider(
            modifier = Modifier
                .height(1.dp)
                .background(Gray)
        )
    }
}

@Composable
fun PlantDetailContentButton(
    text: String,
) {
    Row(
        verticalAlignment = Alignment.CenterVertically
    ) {
        Box(
            modifier = Modifier
                .background(color = MainGreen, shape = CircleShape)
                .size(9.dp)
        )
        Spacer(
            modifier = Modifier.width(3.dp)
        )
        Text(
            text = text,
            style = Typography.suitL15
        )
    }
}


@Preview
@Composable
fun PlantDetailPreview() {
    GreenMateTheme {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 24.dp)
        ) {
            TimelineGroupItem("9월 12일")
        }
    }
}