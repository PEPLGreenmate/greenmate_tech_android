package com.pepl.chat

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.systemBarsPadding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import kotlinx.coroutines.flow.collectLatest

@Composable
internal fun ChatDetailRoute(
    padding: PaddingValues,
    chatRoomId: String,
    onBackClick: () -> Unit,
    onShowErrorSnackBar: (throwable: Throwable?) -> Unit,
    viewModel: ChatDetailViewModel = hiltViewModel(),
) {
    val chatDetailUiState by viewModel.chatDetailUiState.collectAsStateWithLifecycle()

    LaunchedEffect(true) {
        viewModel.errorFlow.collectLatest { throwable -> onShowErrorSnackBar(throwable) }
    }

    ChatDetailScreen(
        padding = padding,
        chatRoomId = chatRoomId,
        onBackClick = onBackClick
    )
}

@Composable
internal fun ChatDetailScreen(
    padding: PaddingValues,
    chatRoomId: String,
    onBackClick: () -> Unit,
    viewModel: ChatDetailViewModel = hiltViewModel(),
) {
    val chatDetailUiState by viewModel.chatDetailUiState.collectAsStateWithLifecycle()


    Box(modifier = Modifier.fillMaxSize()) {

    }

}