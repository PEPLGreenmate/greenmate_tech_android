package com.pepl.home

import com.pepl.chat.navigation.ChatRoute
import com.pepl.diary.navigation.DiaryRoute
import com.pepl.dictionary.navigation.DictionaryRoute
import com.pepl.friend.navigation.FriendRoute
import com.pepl.greenmate.feature.home.R
import com.pepl.manage.navigation.ManageRoute

internal enum class MainTab(
    val iconResId: Int,
    internal val contentDescription: String,
    val route: String,
) {
    FRIEND(
        iconResId = R.drawable.ic_friend,
        contentDescription = "친구",
        FriendRoute.route,
    ),
    CHAT(
        iconResId = R.drawable.ic_chat,
        contentDescription = "채팅",
        ChatRoute.route
    ),
    DIARY(
        iconResId = R.drawable.ic_diary,
        contentDescription = "채팅",
        DiaryRoute.route
    ),
    DICTIONARY(
        iconResId = R.drawable.ic_dictionary,
        contentDescription = "채팅",
        DictionaryRoute.route
    ),
    MANAGE(
        iconResId = R.drawable.ic_manage,
        contentDescription = "채팅",
        ManageRoute.route
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
