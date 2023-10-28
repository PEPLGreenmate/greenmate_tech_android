package com.pepl.setting.navigation

import androidx.compose.foundation.layout.PaddingValues
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.compose.composable
import com.pepl.setting.SettingRoute

fun NavController.navigateSetting(navOptions: NavOptions) {
    this.navigate(SettingRoute.route, navOptions)
}

fun NavGraphBuilder.settingNavGraph(
    padding: PaddingValues,
    onShowErrorSnackBar: (throwable: Throwable?) -> Unit,
) {
    composable(route = SettingRoute.route) {
        SettingRoute(
            padding,
            onShowErrorSnackBar
        )
    }
}


object SettingRoute {
    const val route = "setting"
}