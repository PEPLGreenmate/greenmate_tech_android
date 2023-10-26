package com.pepl.chat

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.systemBarsPadding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
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
import com.pepl.designsystem.theme.Typography
import com.pepl.designsystem.theme.White
import com.pepl.model.Chat
import kotlinx.collections.immutable.toPersistentList
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
        chatDetailUiState = chatDetailUiState,
        chatRoomId = chatRoomId,
        onBackClick = onBackClick
    )
}

@Composable
internal fun ChatDetailScreen(
    padding: PaddingValues,
    chatDetailUiState: ChatDetailUiState,
    chatRoomId: String,
    onBackClick: () -> Unit,
    viewModel: ChatDetailViewModel = hiltViewModel(),
) {

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(BackgroundGreen)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 10.dp),
            horizontalArrangement = Arrangement.Center,
        ) {
            Text(
                text = "페풀이",
                style = Typography.titleLarge,
            )
        }
        Spacer(
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 10.dp),
        )
        when (chatDetailUiState) {
            ChatDetailUiState.Loading -> {
                Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                    CircularProgressIndicator()
                }
            }

            is ChatDetailUiState.Empty -> {
                Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                    Text(
                        text = "채팅이 없습니다"
                    )
                }
            }

            is ChatDetailUiState.Chat -> {
                LazyColumn(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(16.dp),
                    verticalArrangement = Arrangement.spacedBy(13.dp)
                ) {
                    items(chatDetailUiState.chats.toPersistentList()) { chat ->
                        if (chat.isPlantChat) {
                            PlantChat(chat = chat)
                        } else {
                            UserChat(chat = chat)
                        }
                    }
                }
            }
        }
    }

}

@Composable
internal fun PlantChat(
    chat: Chat,
) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.spacedBy(space = 8.dp),
        verticalAlignment = Alignment.CenterVertically
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

        Surface(
            shape = RoundedCornerShape(32.dp),
            color = MainGreen
        ) {
            Text(
                text = chat.message,
                modifier = Modifier
                    .padding(horizontal = 8.dp, vertical = 5.dp),
                color = White
            )
        }
    }
}

@Composable
internal fun UserChat(
    chat: Chat,
) {
    Row(
        modifier = Modifier
            .fillMaxWidth(),
        horizontalArrangement = Arrangement.End
    ) {
        Surface(
            shape = RoundedCornerShape(32.dp),
            color = White
        ) {
            Text(
                text = chat.message,
                modifier = Modifier
                    .padding(horizontal = 8.dp, vertical = 5.dp),
                color = BLACK
            )
        }
    }
}