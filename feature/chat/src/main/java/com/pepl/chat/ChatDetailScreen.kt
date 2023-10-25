package com.pepl.chat

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.systemBarsPadding
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.ColorPainter
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.pepl.designsystem.component.NetworkImage
import com.pepl.designsystem.theme.BLACK
import com.pepl.designsystem.theme.BackgroundGreen
import com.pepl.designsystem.theme.Gray
import com.pepl.designsystem.theme.MainGreen
import com.pepl.designsystem.theme.White
import com.pepl.model.Chat
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

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(BackgroundGreen)
    ) {
        Column(
            modifier = Modifier.fillMaxWidth()
        ) {
            PlantChat(chat = Chat(true, "", "페플", "04:40", "안녕!"))
            UserChat(chat = Chat(false, "", "user", "04:42", "안녕!"))
            PlantChat(chat = Chat(true, "", "페플", "04:43", "안녕!"))
            UserChat(chat = Chat(false, "", "user", "04:44", "안녕!"))
        }
    }
}

@Composable
internal fun PlantChat(
    chat: Chat,
) {
    Row(
        modifier = Modifier.fillMaxWidth()
    ) {
        NetworkImage(
            imageUrl = "",
            placeholder = ColorPainter(Gray),
            modifier = Modifier
                .size(40.dp)
                .clip(CircleShape)
                .background(Gray)
                .clickable(onClick = {})
        )
        Text(
            text = chat.message,
            modifier = Modifier.background(MainGreen),
            color = White
        )
    }

}

@Composable
internal fun UserChat(
    chat: Chat,
) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.End
    ) {
        Text(
            text = chat.message,
            modifier = Modifier.background(Color.White),
            color = BLACK
        )
    }

}