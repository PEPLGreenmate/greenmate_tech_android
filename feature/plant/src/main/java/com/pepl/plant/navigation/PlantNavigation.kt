package com.pepl.plant.navigation

import androidx.compose.foundation.layout.PaddingValues
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.compose.composable
import com.pepl.plant.PlantDetailRoute
import com.pepl.plant.PlantRoute


fun NavController.navigatePlant(navOptions: NavOptions) {
    navigate(PlantRoute.route, navOptions)
}

fun NavController.navigatePlantDetail(roomId: String) {
    navigate(PlantRoute.detailRoute(roomId))
}

fun NavGraphBuilder.plantNavGraph(
    padding: PaddingValues,
    onDetailButtonClick: (String) -> Unit,
    onBackClick: () -> Unit,
    onShowErrorSnackBar: (throwable: Throwable?) -> Unit,
) {
    composable(route = PlantRoute.route) {
        PlantRoute(
            padding,
            onDetailButtonClick = onDetailButtonClick,
            onShowErrorSnackBar = onShowErrorSnackBar
        )
    }

    composable(
        route = PlantRoute.detailRoute("")
    ) { navBackStackEntry ->
        PlantDetailRoute(
            padding = padding,
            plantId = "",
            onBackClick = onBackClick,
            onShowErrorSnackBar = onShowErrorSnackBar
        )
    }
}

object PlantRoute {
    const val route = "plant"

    fun detailRoute(plantId: String): String = "$route/$plantId"
}