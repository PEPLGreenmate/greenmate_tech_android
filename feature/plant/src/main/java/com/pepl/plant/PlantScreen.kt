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
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.snapshotFlow
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.lerp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.pepl.designsystem.component.NetworkImage
import com.pepl.designsystem.theme.Gray
import com.pepl.designsystem.theme.Typography
import com.pepl.designsystem.theme.White
import com.pepl.greenmate.feature.plant.R
import com.pepl.model.Plant
import com.pepl.ui.LoadingScreen
import com.pepl.util.getDiffHour
import kotlinx.coroutines.flow.collectLatest
import kotlin.math.absoluteValue

@Composable
internal fun PlantRoute(
    padding: PaddingValues,
    onShowErrorSnackBar: (throwable: Throwable?) -> Unit,
    onDetailButtonClick: (String) -> Unit,
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

        is PlantUiState.Empty -> {
            EmptyScreen(
                padding = padding,
            )
        }

        else -> {
            PlantScreen(
                padding = padding,
                plantUiState = plantUiState,
                onGridModeClick = {
                    //viewModel.clickGridModeButton()
                },
                onDetailButtonClick = onDetailButtonClick
            )
        }

    }

}

@Composable
private fun EmptyScreen(
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
    onDetailButtonClick: (String) -> Unit,
) {
    val scrollState = rememberScrollState()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .systemBarsPadding()
            .statusBarsPadding()
    ) {
        when (plantUiState) {
            is PlantUiState.GardenEmpty -> {
                PlantHeader(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(start = 16.dp, end = 16.dp, top = 25.dp, bottom = 14.dp),
                    gardenId = plantUiState.gardenId
                )
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
                PlantHeader(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(start = 16.dp, end = 16.dp, top = 25.dp, bottom = 14.dp),
                    gardenId = plantUiState.gardenId
                )
                PlantContents(
                    modifier = Modifier
                        .padding(bottom = 59.dp),
                    plants = plantUiState,
                    onGridModeClick = onGridModeClick,
                    onDetailButtonClick = onDetailButtonClick
                )
            }

            else -> {}
        }
    }
}

@Composable
fun PlantHeader(
    modifier: Modifier,
    gardenId: String,
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
            MyGarden(
                gardenId
            )
            Text(
                text = ":안녕 그린메이트, 잘 지내고 있니?",
                style = Typography.suitL11,
                color = Gray
            )
        }
        AlarmImage(
            modifier = Modifier
                .size(width = 18.dp, height = 18.dp)
                .clickable { },
            isNotReadAlarmExist = true
        )
        Spacer(modifier = Modifier.width(11.dp))
        Image(
            painter = painterResource(id = com.pepl.greenmate.core.designsystem.R.drawable.ic_menu),
            contentDescription = "추가메뉴",
            modifier = Modifier.clickable { }
        )
    }
}

@Composable
fun MyGarden(
    gardenId: String,
) {
    Row(
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            text = gardenId,
            style = Typography.dovemayoR28
        )
        Spacer(
            modifier = Modifier.width(12.dp)
        )
        Image(
            painterResource(id = com.pepl.greenmate.core.designsystem.R.drawable.ic_down_arrow),
            contentDescription = "나의 정원 리스트 보기 아이콘",
            modifier = Modifier.clickable {

            }
        )
    }
}

@Composable
fun PlantContents(
    modifier: Modifier,
    plants: PlantUiState.Plants,
    onGridModeClick: () -> Unit,
    onDetailButtonClick: (String) -> Unit,
) {
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
                PlantDetailMode(
                    plants.plants,
                    onDetailButtonClick
                )
            }

        }
    }
}

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun PlantDetailMode(
    plants: List<Plant>,
    onDetailButtonClick: (String) -> Unit,
) {
    val pagerState = rememberPagerState()
    val contentPadding = (LocalConfiguration.current.screenWidthDp.dp - 168.dp) / 2

    val currentPlant = remember { mutableStateOf(0) }

    LaunchedEffect(pagerState) {
        snapshotFlow { pagerState.currentPage }.collect { page ->
            currentPlant.value = page
        }
    }

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
        PlantDetails(
            plant = plants[currentPlant.value],
            onDetailButtonClick = onDetailButtonClick
        )
    }

}

@Composable
fun PlantGridMode() {

}

@Composable
fun PlantDetails(
    plant: Plant,
    onDetailButtonClick: (String) -> Unit,
) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .fillMaxHeight(1F)
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = plant.name,
                style = Typography.nanumB15
            )
            Spacer(modifier = Modifier.height(5.dp))
            Text(
                text = "${getDiffHour(plant.lastUpdate)}시간 전 기준",
                style = Typography.dovemayoR12,
                color = Gray
            )
            Spacer(modifier = Modifier.height(34.dp))
            PlantStatusChart(
                plant
            )
        }
        Row(
            horizontalArrangement = Arrangement.SpaceAround,
            modifier = Modifier
                .fillMaxWidth()
                .align(Alignment.BottomCenter)
        ) {
            PlantDetailButton(
                "위치 옮기기",
                onClick = {}
            )
            PlantDetailButton(
                "자세히 보기",
                onClick = {
                    onDetailButtonClick(plant.plantId)
                }
            )
            PlantDetailButton(
                "상태 체크하기",
                onClick = {}
            )
        }
    }
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
            imageUrl = plant.imageUrl,
            modifier = modifier
                .aspectRatio(2 / 3f)
                .width(169.dp)
                .clip(RoundedCornerShape(15.dp))
        )
    }
}
