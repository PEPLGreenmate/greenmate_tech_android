package com.pepl.plant

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.layout.systemBarsPadding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.painter.ColorPainter
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.pepl.designsystem.component.NetworkImage
import com.pepl.designsystem.theme.BackgroundGreen
import com.pepl.designsystem.theme.Gray
import com.pepl.designsystem.theme.GreenMateTheme
import com.pepl.designsystem.theme.MainGreen
import kotlinx.collections.immutable.toPersistentList
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
        Column(
            modifier = Modifier
                .fillMaxSize()
                .systemBarsPadding()
        ) {

        }
    }
}

@Composable
fun PlantDetailHeader() {
    val contentSize = (LocalConfiguration.current.screenWidthDp.dp - 168.dp) / 3
    Box(
        modifier = Modifier
            .fillMaxWidth()
    ) {

        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(450.dp)
                .background(MainGreen)
        )
        NetworkImage(
            imageUrl = "https://images.unsplash.com/photo-1512428813834-c702c7702b78?auto=format&fit=crop&q=80&w=1587&ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8fA%3D%3D",
            modifier = Modifier
                .size(width = contentSize * 2, height = 450.dp)
                .clip(
                    RoundedCornerShape(
                        topStart = 0.dp,
                        topEnd = 60.dp,
                        bottomStart = 0.dp,
                        bottomEnd = 60.dp
                    )
                ),
            placeholder = ColorPainter(Gray),
            contentDescription = "식물 사진"
        )

    }
}

@Preview
@Composable
fun PlantDetailPreview() {
    GreenMateTheme {
        Box(
            modifier = Modifier
                .fillMaxSize()
        ) {
            PlantDetailHeader()
        }
    }
}