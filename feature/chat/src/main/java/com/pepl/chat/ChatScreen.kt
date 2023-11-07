package com.pepl.chat

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.pepl.designsystem.theme.BLACK
import com.pepl.designsystem.theme.Gray
import com.pepl.designsystem.theme.GreenMateTheme
import com.pepl.designsystem.theme.Typography
import com.pepl.greenmate.feature.chat.R
import com.pepl.model.ChatRoom
import kotlinx.collections.immutable.toPersistentList
import kotlinx.coroutines.flow.collectLatest

@Composable
internal fun ChatRoute(
    padding: PaddingValues,
    onChatRoomClick: (ChatRoom) -> Unit,
    onShowErrorSnackBar: (throwable: Throwable?) -> Unit,
    viewModel: ChatViewModel = hiltViewModel(),
) {
    val chatUiState by viewModel.chatUiState.collectAsStateWithLifecycle()

    LaunchedEffect(true) {
        viewModel.errorFlow.collectLatest { throwable -> onShowErrorSnackBar(throwable) }
    }

    ChatScreen(
        padding = padding,
        chatUiState = chatUiState,
        onChatRoomClick = onChatRoomClick,
    )
}

@Composable
private fun ChatScreen(
    padding: PaddingValues,
    chatUiState: ChatUiState,
    onChatRoomClick: (ChatRoom) -> Unit,
) {
    Column(
        Modifier
            .padding(padding)
            .padding(horizontal = 8.dp)
            .padding(bottom = 4.dp),
        verticalArrangement = Arrangement.spacedBy(8.dp),
    ) {
        ChatHeader(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 15.dp, bottom = 15.dp, start = 16.dp, end = 16.dp)
        )

        when (chatUiState) {
            ChatUiState.Loading -> {
                Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                    CircularProgressIndicator()
                }
            }

            is ChatUiState.Empty -> {
                Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                    Text(
                        text = "채팅할 수 있는 식물이 없습니다"
                    )
                }
            }

            is ChatUiState.ChatRoom -> {
                LazyColumn {
                    items(chatUiState.chatRooms.toPersistentList()) { chatRoom ->
                        ChatRoomItem(
                            onChatRoomClick = onChatRoomClick,
                            chatRoom = chatRoom,
                        )
                    }
                }
            }
        }

    }
}

@Composable
private fun ChatHeader(
    modifier: Modifier,
) {
    Box(
        modifier = modifier,
    ) {
        Image(
            painter = painterResource(id = com.pepl.greenmate.core.designsystem.R.drawable.main_character),
            contentDescription = null,
            modifier = Modifier
                .size(
                    width = 38.dp,
                    height = 49.dp
                )
                .align(Alignment.CenterStart),

            )
        Column(
            modifier = Modifier.fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(14.dp)
        ) {
            Text(
                text = "채팅",
                style = Typography.titleLarge,
                color = BLACK,
            )
            Text(
                text = "나의 식물과 오늘 하루를 공유하세요",
                style = Typography.bodyMedium,
                color = Color.Gray
            )
        }
    }
}

@Composable
private fun ChatHeaderPreview() {
    GreenMateTheme {
        ChatHeader(
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = 16.dp, top = 15.dp, bottom = 15.dp, end = 16.dp)
                .background(Color.Gray)
        )
    }
}


@Preview
@Composable
private fun ChatScreenPreview() {
    GreenMateTheme {
        LazyColumn {
            items(
                listOf(
                    ChatRoom.createEmpty(),
                    ChatRoom.createEmpty(),
                    ChatRoom.createEmpty()
                )
            ) { chatRoom ->
                ChatRoomItem(
                    chatRoom = chatRoom,
                )
            }
        }
    }
}