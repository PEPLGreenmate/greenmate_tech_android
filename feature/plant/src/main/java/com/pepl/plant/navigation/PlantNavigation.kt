package com.pepl.plant.navigation

import androidx.compose.foundation.layout.PaddingValues
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.compose.composable
import com.pepl.plant.PlantRoute


fun NavController.navigatePlant(navOptions: NavOptions) {
    navigate(PlantRoute.route, navOptions)
}

fun NavGraphBuilder.plantNavGraph(
    padding: PaddingValues,
    onShowErrorSnackBar: (throwable: Throwable?) -> Unit,
) {
    composable(route = PlantRoute.route) {
        PlantRoute(
            padding,
            onShowErrorSnackBar
        )
    }
}

object PlantRoute {
    const val route = "plant"
}