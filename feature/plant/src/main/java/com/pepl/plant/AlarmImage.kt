package com.pepl.plant

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.pepl.designsystem.theme.GreenMateTheme
import com.pepl.designsystem.theme.MainGreen
import com.pepl.greenmate.feature.plant.R

@Composable
fun AlarmImage(
    modifier: Modifier,
    isNotReadAlarmExist: Boolean,
) {
    Box(
        modifier = modifier.fillMaxSize(),
    ) {
        Image(
            painter = painterResource(id = R.drawable.ic_alarm),
            contentDescription = "알림 메시지",
            modifier = modifier,
        )

        if (isNotReadAlarmExist) {
            Box(
                modifier = Modifier
                    .background(color = MainGreen, shape = CircleShape)
                    .size(8.dp)
                    .align(Alignment.TopEnd)
            )
        }
    }

}

@Preview
@Composable
fun PlantScreenPreviews() {
    GreenMateTheme {
        AlarmImage(
            modifier = Modifier.size(width = 18.dp, height = 18.dp),
            isNotReadAlarmExist = true
        )
    }
}