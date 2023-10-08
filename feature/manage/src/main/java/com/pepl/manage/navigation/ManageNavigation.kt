package com.pepl.manage.navigation

import androidx.compose.foundation.layout.PaddingValues
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.compose.composable
import com.pepl.manage.ManageRoute

fun NavController.navigateManage(navOptions: NavOptions) {
    this.navigate(ManageRoute.route, navOptions)
}

fun NavGraphBuilder.manageNavGraph(
    padding: PaddingValues,
    onSessionClick: () -> Unit,
    onContributorClick: () -> Unit,
    onShowErrorSnackBar: (throwable: Throwable?) -> Unit,
) {
    composable(route = ManageRoute.route) {
        ManageRoute(
            padding,
            onSessionClick,
            onContributorClick,
            onShowErrorSnackBar
        )
    }
}


object ManageRoute {
    const val route = "manage"
}