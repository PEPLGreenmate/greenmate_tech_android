package com.pepl.chat.navigation

import androidx.compose.foundation.layout.PaddingValues
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.NavType
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.pepl.chat.ChatDetailRoute
import com.pepl.chat.ChatRoute
import com.pepl.chat.navigation.ChatRoute
import com.pepl.model.ChatRoom

fun NavController.navigateChat(navOptions: NavOptions) {
    navigate(ChatRoute.route, navOptions)
}

fun NavController.navigateChatDetail(roomId: String) {
    navigate(ChatRoute.detailRoute(roomId))
}

fun NavGraphBuilder.chatNavGraph(
    padding: PaddingValues,
    onBackClick: () -> Unit,
    onChatRoomClick: (ChatRoom) -> Unit,
    onShowErrorSnackBar: (throwable: Throwable?) -> Unit,
) {
    composable(route = ChatRoute.route) {
        ChatRoute(
            padding = padding,
            onChatRoomClick = onChatRoomClick,
            onShowErrorSnackBar = onShowErrorSnackBar
        )
    }

    composable(
        route = ChatRoute.detailRoute("")
    ) { navBackStackEntry ->
        ChatDetailRoute(
            padding = padding,
            chatRoomId = "",
            onBackClick = onBackClick,
            onShowErrorSnackBar = onShowErrorSnackBar
        )
    }
}

object ChatRoute {
    const val route = "chat"

    fun detailRoute(roomId: String): String = "$route/$roomId"
}
