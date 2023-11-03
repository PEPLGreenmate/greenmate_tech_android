package com.pepl.plant

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.pepl.designsystem.theme.BackgroundGreen
import com.pepl.designsystem.theme.GreenMateTheme
import com.pepl.designsystem.theme.MainGreen
import com.pepl.designsystem.theme.Typography
import com.pepl.designsystem.theme.White

@Composable
fun PlantDetailButton(
    text: String,
    onClick: () -> Unit,
) {
    Box(
        modifier = Modifier
            .padding(vertical = 0.dp)
            .clip(
                RoundedCornerShape(
                    topStart = 15.dp,
                    topEnd = 15.dp,
                    bottomStart = 0.dp,
                    bottomEnd = 0.dp
                )
            )
            .background(MainGreen)
            .clickable {
                onClick()
            }
    ) {
        Text(
            modifier = Modifier.padding(horizontal = 16.dp, vertical = 13.dp),
            text = text,
            style = Typography.bodyMedium,
            color = White
        )
    }
}

@Preview
@Composable
fun PlantDetailButtonPreview() {
    GreenMateTheme {
        PlantDetailButton(
            "자세히 보기",
            onClick = {}
        )
    }
}