package com.pepl.plant

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.layout.systemBarsPadding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
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
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.pepl.designsystem.component.NetworkImage
import com.pepl.designsystem.theme.BackgroundGreen
import com.pepl.designsystem.theme.Gray
import com.pepl.designsystem.theme.GreenMateTheme
import com.pepl.designsystem.theme.MainGreen
import com.pepl.designsystem.theme.Typography
import com.pepl.greenmate.feature.plant.R
import com.pepl.model.Plant
import com.pepl.model.PlantStatus
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
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp)
                .padding(bottom = 22.dp),
            horizontalArrangement = Arrangement.End
        ) {
            Image(
                painter = painterResource(id = R.drawable.ic_grid),
                contentDescription = "내 식물 리스트 그리드로 보기"
            )
        }
        PlantContents(
            modifier = Modifier
                .padding(bottom = 59.dp),
            plants = listOf(
                Plant("", 0L, "", PlantStatus(0, 0, 0, 0)),
                Plant("", 0L, "", PlantStatus(0, 0, 0, 0)),
                Plant("", 0L, "", PlantStatus(0, 0, 0, 0)),
                Plant("", 0L, "", PlantStatus(0, 0, 0, 0))
            )
        )
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
    plants: List<Plant>,
) {
    Box(
        modifier = modifier
            .fillMaxHeight(1F)
    ) {
        Column {
            LazyRow(
                modifier = Modifier
                    .fillMaxWidth(),
                contentPadding = PaddingValues(start = 12.dp, end = 12.dp),
                horizontalArrangement = Arrangement.spacedBy(24.dp)
            ) {
                items(plants) { plant ->
                    PlantRowItem(plant, onItemClick = {}, modifier = Modifier)
                }
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
fun PlantRowItem(
    plant: Plant,
    onItemClick: (Plant) -> Unit,
    modifier: Modifier,
) {
    Surface(
        modifier = Modifier
            .size(width = 168.dp, height = 219.dp),
        shape = RoundedCornerShape(15.dp),
        color = BackgroundGreen,
    ) {
        NetworkImage(
            imageUrl = "https://images.unsplash.com/photo-1512428813834-c702c7702b78?auto=format&fit=crop&q=80&w=1587&ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8fA%3D%3D",
            modifier = Modifier.fillMaxSize()
        )
    }
}

@Preview
@Composable
fun PlantScreenPreview() {
    GreenMateTheme {
        Column {
            PlantContents(
                modifier = Modifier
                    .fillMaxWidth(),
                plants = listOf(
                    Plant("", 0L, "", PlantStatus(0, 0, 0, 0)),
                    Plant("", 0L, "", PlantStatus(0, 0, 0, 0)),
                    Plant("", 0L, "", PlantStatus(0, 0, 0, 0)),
                    Plant("", 0L, "", PlantStatus(0, 0, 0, 0))
                )

            )
        }

    }
}