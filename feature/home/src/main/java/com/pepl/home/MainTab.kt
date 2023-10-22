package com.pepl.home

import com.pepl.chat.navigation.ChatRoute
import com.pepl.plant.navigation.PlantRoute
import com.pepl.diary.navigation.DiaryRoute
import com.pepl.setting.navigation.SettingRoute
import com.pepl.greenmate.feature.home.R

internal enum class MainTab(
    val iconResId: Int,
    internal val contentDescription: String,
    val route: String,
) {
    CHAT(
        iconResId = R.drawable.ic_chat,
        contentDescription = "채팅",
        ChatRoute.route
    ),
    PLANT(
        iconResId = R.drawable.ic_plant,
        contentDescription = "식물관리",
        PlantRoute.route,
    ),
    DIARY(
        iconResId = R.drawable.ic_diary,
        contentDescription = "다이어리",
        DiaryRoute.route
    ),
    SETTING(
        iconResId = R.drawable.ic_setting,
        contentDescription = "설정",
        SettingRoute.route
    );

    companion object {
        operator fun contains(route: String): Boolean {
            return values().map { it.route }.contains(route)
        }

        fun find(route: String): MainTab? {
            return values().find { it.route == route }
        }
    }
}
