package com.pepl.plant

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.pepl.designsystem.theme.Blue
import com.pepl.designsystem.theme.Gold
import com.pepl.designsystem.theme.Gray
import com.pepl.designsystem.theme.GreenMateTheme
import com.pepl.designsystem.theme.LightGray
import com.pepl.designsystem.theme.Mint
import com.pepl.designsystem.theme.RedPurple
import com.pepl.designsystem.theme.Typography
import com.pepl.greenmate.feature.plant.R

@Composable
fun PlantStatusChart(

) {
    Row(
        modifier = Modifier
            .fillMaxWidth(),
        horizontalArrangement = Arrangement.spacedBy(23.dp, Alignment.CenterHorizontally)
    ) {
        PlantCharItem(
            "토양 수분",
            0.7f,
            Mint,
            R.drawable.ic_soilwater,
            "토양 수분 아이콘",
            "30%",
            "적정"
        )
        PlantCharItem(
            "조도",
            0.3f,
            Gold,
            R.drawable.ic_sun,
            "조도 아이콘",
            "150",
            "적정"
        )
        PlantCharItem(
            "온도",
            0.8f,
            RedPurple,
            R.drawable.ic_temperature,
            "온도 아이콘",
            "34℃",
            "너무 더워요!"
        )
        PlantCharItem(
            "습도",
            0.5f,
            Blue,
            R.drawable.ic_humidity,
            "습도 아이콘",
            "50%",
            "적정"
        )
    }
}

@Composable
fun PlantCharItem(
    title: String,
    progress: Float,
    progressColor: Color,
    resourceId: Int,
    contentDescription: String,
    progressText: String,
    description: String,
) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = title,
            style = Typography.titleMedium
        )
        Spacer(
            modifier = Modifier.height(11.dp)
        )
        Box(
            contentAlignment = Alignment.Center
        ) {
            CircularProgressIndicator(
                progress = progress,
                modifier = Modifier.size(60.dp),
                color = progressColor,
                trackColor = LightGray,
            )
            Image(
                painter = painterResource(id = resourceId),
                contentDescription = contentDescription,
                modifier = Modifier.padding(8.dp)
            )
        }
        Spacer(
            modifier = Modifier.height(12.dp)
        )
        Text(
            text = progressText,
            style = Typography.bodyMedium
        )
        Spacer(
            modifier = Modifier.height(19.dp)
        )
        Text(
            text = description,
            style = Typography.bodyMedium,
            color = Gray
        )
    }
}

@Preview
@Composable
fun PlantChartPreview() {
    GreenMateTheme {
        PlantStatusChart()
    }
}