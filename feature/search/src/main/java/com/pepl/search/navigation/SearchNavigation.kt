package com.pepl.search.navigation

import androidx.compose.foundation.layout.PaddingValues
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.compose.composable
import com.pepl.search.SearchRoute

fun NavController.navigateSearch(navOptions: NavOptions) {
    this.navigate(SearchRoute.route, navOptions)
}

fun NavGraphBuilder.searchNavGraph(
    padding: PaddingValues,
    onShowErrorSnackBar: (throwable: Throwable?) -> Unit,
) {
    composable(route = SearchRoute.route) {
        SearchRoute(
            padding,
            onShowErrorSnackBar
        )
    }
}


object SearchRoute {
    const val route = "search"
}