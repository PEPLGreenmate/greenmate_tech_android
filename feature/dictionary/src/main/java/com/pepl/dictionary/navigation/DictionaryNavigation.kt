package com.pepl.dictionary.navigation

import androidx.navigation.NavController
import androidx.navigation.NavOptions

fun NavController.navigateDictionary(navOptions: NavOptions) {
    this.navigate(DictionaryRoute.route, navOptions)
}

object DictionaryRoute {
    const val route = "dictionary"
}