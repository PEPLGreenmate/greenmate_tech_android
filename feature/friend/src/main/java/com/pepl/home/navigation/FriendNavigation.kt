package com.pepl.home.navigation

import androidx.compose.foundation.layout.PaddingValues
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.compose.composable
import com.pepl.home.FriendRoute


fun NavController.navigateFriend(navOptions: NavOptions) {
    navigate(FriendRoute.route, navOptions)
}

fun NavGraphBuilder.friendNavGraph(
    padding: PaddingValues,
    onSessionClick: () -> Unit,
    onContributorClick: () -> Unit,
    onShowErrorSnackBar: (throwable: Throwable?) -> Unit,
) {
    composable(route = FriendRoute.route) {
        FriendRoute(
            padding,
            onSessionClick,
            onContributorClick,
            onShowErrorSnackBar
        )
    }
}

object FriendRoute {
    const val route = "friend"
}