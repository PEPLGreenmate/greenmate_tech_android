package com.pepl.designsystem.theme

import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import com.pepl.greenmate.core.designsystem.R
import javax.annotation.concurrent.Immutable

private val dovemayoFamily = FontFamily(
    Font(R.font.dovemayo_gothic, FontWeight.Normal)
)

private val nanumFamily = FontFamily(
    Font(R.font.nanum_square_round_light, FontWeight.Light),
    Font(R.font.nanum_square_round_regular, FontWeight.Normal),
    Font(R.font.nanum_square_round_bold, FontWeight.Bold)
)

private val suitFamily = FontFamily(
    Font(R.font.suit_light, FontWeight.Light),
    Font(R.font.suit_medium, FontWeight.Medium),
    Font(R.font.suit_bold, FontWeight.Bold)
)

private val pretendardFamily = FontFamily(
    Font(R.font.pretendatd_medium, FontWeight.Medium)
)

private val dovemayoStyle = TextStyle(
    fontFamily = dovemayoFamily
)

private val nanumStyle = TextStyle(
    fontFamily = nanumFamily
)

private val suitStyle = TextStyle(
    fontFamily = suitFamily
)

private val pretendardStyle = TextStyle(
    fontFamily = pretendardFamily
)

val Typography = GreenMateTypography(
    dovemayoR12 = dovemayoStyle.copy(
        fontSize = 12.sp,
    ),
    dovemayoR14 = dovemayoStyle.copy(
        fontSize = 14.sp,
    ),
    dovemayoR17 = dovemayoStyle.copy(
        fontSize = 17.sp,
    ),
    dovemayoR18 = dovemayoStyle.copy(
        fontSize = 18.sp,
    ),
    dovemayoR19 = dovemayoStyle.copy(
        fontSize = 19.sp,
    ),
    dovemayoR26 = dovemayoStyle.copy(
        fontSize = 26.sp,
    ),
    dovemayoR28 = dovemayoStyle.copy(
        fontSize = 28.sp,
    ),
    dovemayoR36 = dovemayoStyle.copy(
        fontSize = 36.sp,
    ),
    dovemayoR40 = dovemayoStyle.copy(
        fontSize = 40.sp,
    ),
    nanumL10 = nanumStyle.copy(
        fontSize = 10.sp,
        fontWeight = FontWeight.Light
    ),
    nanumR8 = nanumStyle.copy(
        fontSize = 8.sp,
        fontWeight = FontWeight.Normal
    ),
    nanumR10 = nanumStyle.copy(
        fontSize = 10.sp,
        fontWeight = FontWeight.Normal
    ),
    nanumR13 = nanumStyle.copy(
        fontSize = 13.sp,
        fontWeight = FontWeight.Normal
    ),
    nanumB11 = nanumStyle.copy(
        fontSize = 11.sp,
        fontWeight = FontWeight.Bold
    ),
    nanumB12 = nanumStyle.copy(
        fontSize = 12.sp,
        fontWeight = FontWeight.Bold
    ),
    nanumB15 = nanumStyle.copy(
        fontSize = 15.sp,
        fontWeight = FontWeight.Bold
    ),
    suitL11 = suitStyle.copy(
        fontSize = 11.sp,
        fontWeight = FontWeight.Light
    ),
    pretendardM12 = pretendardStyle.copy(
        fontSize = 12.sp,
        fontWeight = FontWeight.Medium
    ),
    pretendardM25 = pretendardStyle.copy(
        fontSize = 25.sp,
        fontWeight = FontWeight.Medium
    )
)

@Immutable
data class GreenMateTypography(
    val dovemayoR12: TextStyle,
    val dovemayoR14: TextStyle,
    val dovemayoR17: TextStyle,
    val dovemayoR18: TextStyle,
    val dovemayoR19: TextStyle,
    val dovemayoR26: TextStyle,
    val dovemayoR28: TextStyle,
    val dovemayoR36: TextStyle,
    val dovemayoR40: TextStyle,
    val nanumL10: TextStyle,
    val nanumR8: TextStyle,
    val nanumR10: TextStyle,
    val nanumR13: TextStyle,
    val nanumB11: TextStyle,
    val nanumB12: TextStyle,
    val nanumB15: TextStyle,
    val suitL11: TextStyle,
    val pretendardM12: TextStyle,
    val pretendardM25: TextStyle,
)

val LocalTypography = staticCompositionLocalOf { Typography }
