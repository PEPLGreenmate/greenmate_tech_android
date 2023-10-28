package com.pepl.diary.navigation

import androidx.compose.foundation.layout.PaddingValues
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.compose.composable
import com.pepl.diary.DiaryRoute

fun NavController.navigateDiary(navOptions: NavOptions) {
    this.navigate(DiaryRoute.route, navOptions)
}

fun NavGraphBuilder.diaryNavGraph(
    padding: PaddingValues,
    onShowErrorSnackBar: (throwable: Throwable?) -> Unit,
) {
    composable(route = DiaryRoute.route) {
        DiaryRoute(
            padding,
            onShowErrorSnackBar
        )
    }
}

object DiaryRoute {
    const val route = "diary"
}