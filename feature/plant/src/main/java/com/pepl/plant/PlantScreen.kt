package com.pepl.plant

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.layout.systemBarsPadding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults.elevatedCardColors
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.lerp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.pepl.designsystem.component.NetworkImage
import com.pepl.designsystem.theme.BackgroundGreen
import com.pepl.designsystem.theme.Gray
import com.pepl.designsystem.theme.GreenMateTheme
import com.pepl.designsystem.theme.Typography
import com.pepl.designsystem.theme.White
import com.pepl.greenmate.feature.plant.R
import com.pepl.model.Plant
import com.pepl.model.PlantStatus
import com.pepl.ui.LoadingScreen
import kotlinx.coroutines.flow.collectLatest
import kotlin.math.absoluteValue

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

    when (plantUiState) {
        PlantUiState.Loading -> {
            LoadingScreen()
        }

        is PlantUiState.GardenEmpty -> {
            EmptyGardenScreen(
                padding = padding,
            )
        }

        else -> {
            PlantScreen(
                padding = padding,
                plantUiState = plantUiState,
                onGridModeClick = {
                    //viewModel.clickGridModeButton()
                }
            )
        }

    }

}

@Composable
private fun EmptyGardenScreen(
    padding: PaddingValues,
) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(padding), contentAlignment = Alignment.Center
    ) {
        Text(
            textAlign = TextAlign.Center,
            text = "그린메이트가 처음이신가요?\n정원을 만들어 식물을 추가해보세요!"
        )
    }
}

@Composable
private fun PlantScreen(
    padding: PaddingValues,
    plantUiState: PlantUiState,
    onGridModeClick: () -> Unit,
) {
    val scrollState = rememberScrollState()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .systemBarsPadding()
            .statusBarsPadding()
    ) {
        PlantHeader(
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = 16.dp, end = 16.dp, top = 25.dp, bottom = 14.dp)
        )
        when (plantUiState) {
            is PlantUiState.Empty -> {
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .fillMaxHeight(1F), contentAlignment = Alignment.Center
                ) {
                    Text(
                        textAlign = TextAlign.Center,
                        text = "아직 정원에 식물이 없습니다"
                    )
                }
            }

            is PlantUiState.Plants -> {
                PlantContents(
                    modifier = Modifier
                        .padding(bottom = 59.dp),
                    plants = plantUiState,
                    onGridModeClick = onGridModeClick
                )
            }

            else -> {}
        }
    }
}

@Composable
fun PlantHeader(
    modifier: Modifier,
) {
    Row(
        modifier = modifier.fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Image(
            painter = painterResource(id = com.pepl.greenmate.core.designsystem.R.drawable.main_character),
            contentDescription = "메인 캐릭터 이미지",
            modifier = Modifier.size(width = 38.dp, height = 49.dp)
        )
        Spacer(modifier = Modifier.width(5.dp))
        Column(
            modifier = Modifier.weight(1F)
        ) {
            Text(
                text = "회사 책상 위",
                style = Typography.titleLarge
            )
            Text(
                text = ":안녕 그린메이트, 잘 지내고 있니?",
                style = Typography.bodyMedium,
                color = Gray
            )
        }
        AlarmImage(
            modifier = Modifier.size(width = 18.dp, height = 18.dp),
            isNotReadAlarmExist = true
        )
        Spacer(modifier = Modifier.width(11.dp))
        Image(
            painter = painterResource(id = R.drawable.ic_menu),
            contentDescription = "추가메뉴"
        )
    }
}

@Composable
fun PlantContents(
    modifier: Modifier,
    plants: PlantUiState.Plants,
    onGridModeClick: () -> Unit,
) {
//    LaunchedEffect(pagerState) {
//        // Collect from the a snapshotFlow reading the currentPage
//        snapshotFlow { pagerState.currentPage }.collect { page ->
//            // Do something with each page change, for example:
//            // viewModel.sendPageSelectedEvent(page)
//            Log.d("Page change", "Page changed to $page")
//        }
//    }


    Box(
        modifier = modifier
            .fillMaxHeight(1F)
    ) {
        Column {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp)
                    .padding(bottom = 22.dp),
                horizontalArrangement = Arrangement.End
            ) {
                Image(
                    painter = painterResource(id = R.drawable.ic_grid),
                    contentDescription = "내 식물 리스트 그리드로 보기",
                    modifier = Modifier.clickable(onClick = onGridModeClick)
                )
            }

            if (plants.isGridMode) {
                PlantGridMode()
            } else {
                PlantDetailMode(plants.plants)
            }

        }
    }
}

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun PlantDetailMode(
    plants: List<Plant>,
) {
    val pagerState = rememberPagerState()
    val contentPadding = (LocalConfiguration.current.screenWidthDp.dp - 168.dp) / 2

    Column() {
        HorizontalPager(
            state = pagerState,
            pageCount = plants.size,
            contentPadding = PaddingValues(horizontal = contentPadding),
            pageSpacing = 40.dp
        ) { index ->

            PlantRowItem(
                plants[index],
                onItemClick = {},
                modifier = Modifier
                    .fillMaxWidth()
                    .graphicsLayer {
                        val pageOffset = (
                                (pagerState.currentPage - index) + pagerState
                                    .currentPageOffsetFraction
                                ).absoluteValue
                        lerp(
                            start = 0.85f.dp,
                            stop = 1f.dp,
                            fraction = 1f - pageOffset.coerceIn(0f, 1f)
                        ).also { scale ->
                            scaleX = scale.value
                            scaleY = scale.value
                        }

                        alpha = lerp(
                            start = 0.75f.dp,
                            stop = 1f.dp,
                            fraction = 1f - pageOffset.coerceIn(0f, 1f)
                        ).value

                    }
            )
        }
        Spacer(modifier = Modifier.height(19.dp))
        Column(
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = "플래그먼트",
                style = Typography.titleLarge
            )
            Spacer(modifier = Modifier.height(5.dp))
            Text(
                text = "2시간 전 기준",
                style = Typography.bodySmall,
                color = Gray
            )
            Spacer(modifier = Modifier.height(34.dp))
            PlantStatusChart()
        }
        Row(
            horizontalArrangement = Arrangement.SpaceAround,
            modifier = Modifier
                .fillMaxWidth()
                .align(Alignment.CenterHorizontally)
        ) {
            PlantDetailButton(
                "위치 옮기기",
                onClick = {}
            )
            PlantDetailButton(
                "자세히 보기",
                onClick = {}
            )
            PlantDetailButton(
                "상태 체크하기l",
                onClick = {}
            )
        }
    }

}

@Composable
fun PlantGridMode() {

}

@Composable
fun PlantRowItem(
    plant: Plant,
    onItemClick: (Plant) -> Unit,
    modifier: Modifier,
) {
    Card(
        modifier = Modifier.wrapContentHeight(),
        shape = RoundedCornerShape(15.dp),
        colors = elevatedCardColors(containerColor = White)
    ) {
        NetworkImage(
            imageUrl = "https://images.unsplash.com/photo-1512428813834-c702c7702b78?auto=format&fit=crop&q=80&w=1587&ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8fA%3D%3D",
            modifier = modifier
                .aspectRatio(2 / 3f)
                .width(169.dp)
                .clip(RoundedCornerShape(15.dp))
        )
    }
}
