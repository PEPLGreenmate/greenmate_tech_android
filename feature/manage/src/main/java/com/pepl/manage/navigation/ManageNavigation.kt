package com.pepl.manage.navigation

import androidx.navigation.NavController
import androidx.navigation.NavOptions

fun NavController.navigateManage(navOptions: NavOptions) {
    this.navigate(ManageRoute.route, navOptions)
}

object ManageRoute {
    const val route = "manage"
}