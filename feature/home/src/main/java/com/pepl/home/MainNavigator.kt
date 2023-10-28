package com.pepl.home

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.navigation.NavDestination
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navOptions
import com.pepl.chat.navigation.navigateChat
import com.pepl.chat.navigation.navigateChatDetail
import com.pepl.diary.navigation.navigateDiary
import com.pepl.plant.navigation.PlantRoute
import com.pepl.plant.navigation.navigatePlant
import com.pepl.setting.navigation.navigateSetting

internal class MainNavigator(
    val navController: NavHostController,
) {
    private val currentDestination: NavDestination?
        @Composable get() = navController.currentBackStackEntryAsState().value?.destination

    val startDestination = MainTab.PLANT.route

    val currentTab: MainTab?
        @Composable get() = currentDestination
            ?.route
            ?.let(MainTab.Companion::find)

    fun navigate(tab: MainTab) {
        val navOptions = navOptions {
            popUpTo(navController.graph.findStartDestination().id) {
                saveState = true
            }
            launchSingleTop = true
            restoreState = true
        }

        when (tab) {
            MainTab.CHAT -> navController.navigateChat(navOptions)
            MainTab.PLANT -> navController.navigatePlant(navOptions)
            MainTab.DIARY -> navController.navigateDiary(navOptions)
            MainTab.SETTING -> navController.navigateSetting(navOptions)
        }
    }

    fun navigateChatDetail(roomId: String) {
        navController.navigateChatDetail(roomId)
    }

    fun popBackStack() {
        navController.popBackStack()
    }

    fun popBackStackIfNotHome() {
        if (!isSameCurrentDestination(PlantRoute.route)) {
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
    navController: NavHostController = rememberNavController(),
): MainNavigator = remember(navController) {
    MainNavigator(navController)
}