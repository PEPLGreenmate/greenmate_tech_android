package com.pepl.chat

import androidx.compose.foundation.Image
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
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.layout.systemBarsPadding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.itemsIndexed
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
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.pepl.designsystem.component.NetworkImage
import com.pepl.designsystem.theme.BLACK
import com.pepl.designsystem.theme.BackgroundGreen
import com.pepl.designsystem.theme.DarkBrown
import com.pepl.designsystem.theme.Gray
import com.pepl.designsystem.theme.GreenMateTheme
import com.pepl.designsystem.theme.MainGreen
import com.pepl.designsystem.theme.Typography
import com.pepl.designsystem.theme.White
import com.pepl.greenmate.feature.chat.R
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
        Spacer(
            modifier = Modifier.statusBarsPadding()
        )
        ChatHeader(
            modifier = Modifier.padding(32.dp),
        )
        Spacer(
            modifier = Modifier
                .height(10.dp)
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
                    itemsIndexed(chatDetailUiState.chats.toPersistentList()) { index, chat ->
                        if (chat.isPlantChat) {
                            PlantChat(
                                chat = chat,
                                isLastChat = index == chatDetailUiState.chats.size - 1 || chatDetailUiState.chats[index + 1].isPlantChat.not()
                            )
                        } else {
                            UserChat(
                                chat = chat,
                                isLastChat = index == chatDetailUiState.chats.size - 1 || chatDetailUiState.chats[index + 1].isPlantChat
                            )
                        }
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
    Row(
        modifier = modifier
            .fillMaxWidth()
            .padding(top = 10.dp),
        horizontalArrangement = Arrangement.Center,
        verticalAlignment = Alignment.CenterVertically
    ) {

        Image(
            painterResource(id = com.pepl.greenmate.core.designsystem.R.drawable.left_arrow),
            contentDescription = "뒤로 가기 버튼",
            modifier = Modifier.size(13.dp)
        )
        Text(
            text = "페풀이",
            style = Typography.titleLarge,
            modifier = Modifier.fillMaxWidth(1F),
            textAlign = TextAlign.Center
        )
        Image(
            painterResource(id = com.pepl.greenmate.core.designsystem.R.drawable.main_character),
            contentDescription = "추가 옵션 버튼",
        )
    }
}


@Composable
internal fun PlantChat(
    chat: Chat,
    isLastChat: Boolean,
) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        verticalAlignment = Alignment.Bottom
    ) {
        if (isLastChat) {
            NetworkImage(
                imageUrl = chat.imageUrl,
                placeholder = ColorPainter(Gray),
                modifier = Modifier
                    .size(40.dp)
                    .clip(CircleShape)
                    .background(Gray)
                    .clickable(onClick = {})
            )
        } else {
            Spacer(
                modifier = Modifier
                    .size(40.dp)
            )
        }
        Spacer(
            modifier = Modifier
                .size(14.dp)
        )
        Surface(
            shape = if (isLastChat) {
                RoundedCornerShape(
                    topStart = 16.dp,
                    topEnd = 32.dp,
                    bottomStart = 0.dp,
                    bottomEnd = 32.dp
                )
            } else {
                RoundedCornerShape(32.dp)
            },
            color = White
        ) {
            Text(
                text = chat.message,
                style = Typography.bodyMedium,
                modifier = Modifier
                    .padding(horizontal = 20.dp, vertical = 10.dp),
                color = BLACK
            )
        }
        Spacer(
            modifier = Modifier.width(5.dp)
        )
        Text(
            text = chat.sendTime,
            style = Typography.bodySmall,
            color = DarkBrown,
        )
    }
}

@Composable
internal fun UserChat(
    chat: Chat,
    isLastChat: Boolean,
) {
    Row(
        modifier = Modifier
            .fillMaxWidth(),
        horizontalArrangement = Arrangement.End,
        verticalAlignment = Alignment.Bottom
    ) {

        Text(
            text = chat.sendTime,
            style = Typography.bodySmall,
            color = DarkBrown,
        )
        Spacer(
            modifier = Modifier.width(5.dp)
        )
        Surface(
            shape = if (isLastChat) {
                RoundedCornerShape(
                    topStart = 32.dp,
                    topEnd = 16.dp,
                    bottomStart = 32.dp,
                    bottomEnd = 0.dp
                )
            } else {
                RoundedCornerShape(32.dp)
            },
            color = MainGreen
        ) {
            Text(
                text = chat.message,
                style = Typography.bodyMedium,
                modifier = Modifier
                    .padding(
                        horizontal = 20.dp, vertical = 10.dp
                    ),
                color = White
            )
        }

    }
}
