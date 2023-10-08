package com.pepl.chat.navigation

import androidx.compose.foundation.layout.PaddingValues
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.compose.composable
import com.pepl.chat.ChatRoute

fun NavController.navigateChat(navOptions: NavOptions) {
    navigate(ChatRoute.route, navOptions)
}

fun NavGraphBuilder.chatNavGraph(
    padding: PaddingValues,
    onSessionClick: () -> Unit,
    onContributorClick: () -> Unit,
    onShowErrorSnackBar: (throwable: Throwable?) -> Unit,
) {
    composable(route = ChatRoute.route) {
        ChatRoute(
            padding,
            onSessionClick,
            onContributorClick,
            onShowErrorSnackBar
        )
    }
}


object ChatRoute {
    const val route = "chat"
}
