package com.pepl.dictionary.navigation

import androidx.compose.foundation.layout.PaddingValues
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.compose.composable
import com.pepl.dictionary.DictionaryRoute

fun NavController.navigateDictionary(navOptions: NavOptions) {
    this.navigate(DictionaryRoute.route, navOptions)
}

fun NavGraphBuilder.dictionaryNavGraph(
    padding: PaddingValues,
    onSessionClick: () -> Unit,
    onContributorClick: () -> Unit,
    onShowErrorSnackBar: (throwable: Throwable?) -> Unit,
) {
    composable(route = DictionaryRoute.route) {
        DictionaryRoute(
            padding,
            onSessionClick,
            onContributorClick,
            onShowErrorSnackBar
        )
    }
}

object DictionaryRoute {
    const val route = "dictionary"
}