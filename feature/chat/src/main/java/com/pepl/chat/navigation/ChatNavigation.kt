package com.pepl.chat.navigation

import androidx.navigation.NavController
import androidx.navigation.NavOptions

fun NavController.navigateChat(navOptions: NavOptions) {
    this.navigate(ChatRoute.route, navOptions)
}

object ChatRoute {
    const val route = "chat"
}
