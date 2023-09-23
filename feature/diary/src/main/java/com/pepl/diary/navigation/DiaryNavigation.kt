package com.pepl.diary.navigation

import androidx.navigation.NavController
import androidx.navigation.NavOptions

fun NavController.navigateDiary(navOptions: NavOptions) {
    this.navigate(DiaryRoute.route, navOptions)
}

object DiaryRoute {
    const val route = "diary"
}