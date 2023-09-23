package com.pepl.greenmate

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.navigation.NavController
import androidx.navigation.NavDestination
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navOptions
import com.pepl.chat.navigation.navigateChat
import com.pepl.diary.navigation.navigateDiary
import com.pepl.dictionary.navigation.navigateDictionary
import com.pepl.home.navigation.FriendRoute
import com.pepl.home.navigation.navigateFriend
import com.pepl.manage.navigation.navigateManage

internal class MainNavigator(
    val navController: NavController,
) {
    private val currentDestination: NavDestination?
        @Composable get() = navController.currentBackStackEntryAsState().value?.destination

    val startDestination = MainTab.FRIEND.route

    val currentTab: MainTab?
        @Composable get() = currentDestination
            ?.route
            ?.let(MainTab::find)

    fun navigate(tab: MainTab) {
        val navOptions = navOptions {
            popUpTo(navController.graph.findStartDestination().id) {
                saveState = true
            }
            launchSingleTop = true
            restoreState = true
        }

        when (tab) {
            MainTab.FRIEND -> navController.navigateFriend(navOptions)
            MainTab.CHAT -> navController.navigateChat(navOptions)
            MainTab.DIARY -> navController.navigateDiary(navOptions)
            MainTab.DICTIONARY -> navController.navigateDictionary(navOptions)
            MainTab.MANAGE -> navController.navigateManage(navOptions)
        }
    }

    fun popBackStack() {
        navController.popBackStack()
    }

    fun popBackStackIfNotHome() {
        if (!isSameCurrentDestination(FriendRoute.route)) {
            popBackStack()
        }
    }

    private fun isSameCurrentDestination(route: String) =
        navController.currentDestination?.route == route

    @Composable
    fun shouldShowBottomBar(): Boolean {
        val currentRoute = currentDestination?.route ?: return false
        return currentRoute in MainTab
    }
}

@Composable
internal fun rememberMainNavigator(
    navController: NavController = rememberNavController(),
): MainNavigator = remember(navController) {
    MainNavigator(navController)
}