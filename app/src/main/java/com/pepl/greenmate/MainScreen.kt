package com.pepl.greenmate

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.navigation.NavHost
import androidx.navigation.compose.NavHost
import com.pepl.greenmate.ui.theme.Purple80
import kotlinx.coroutines.launch

@Composable
internal fun MainScreen(
    navigator: MainNavigator = rememberMainNavigator(),
) {
    val snackBarHostState = remember { SnackbarHostState() }
    val coroutineScope = rememberCoroutineScope()

    val onShowErrorSnackGar: (throwable: Throwable?) -> Unit = { throwable ->
        coroutineScope.launch {
            snackBarHostState.showSnackbar(
                "알 수 없는 오류가 발생하였습니다"
            )
        }
    }

//    Scaffold(
//        content = { padding ->
//            Box(
//                modifier = Modifier
//                    .fillMaxSize()
//                    .background(Purple80)
//            ) {
//                NavHost(
//                    navController = navigator.navController,
//                    startDestination = navigator.startDestination
//                ) {
//
//                }
//            }
//        },
//        bottomBar = {
//            MainBottomBar(
//                visible = navigator.shouldShowBottomBar(),
//                tabs = MainTab.values().toList().toPersistentList(),
//                currentTab = navigator.currentTab,
//                onTabSelected = { navigator.navigate(it) }
//            )
//        },
//        snackbarHost = { SnackbarHost(hostState = snackBarHostState) }
//    )
}