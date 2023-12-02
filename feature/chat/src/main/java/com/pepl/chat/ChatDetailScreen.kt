package com.pepl.chat

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
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
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
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
import com.pepl.designsystem.theme.DarkGray
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
        onBackClick = onBackClick,
        onSendClick = {
            viewModel.sendChat(it)
        }
    )
}

@Composable
internal fun ChatDetailScreen(
    padding: PaddingValues,
    chatDetailUiState: ChatDetailUiState,
    chatRoomId: String,
    onBackClick: () -> Unit,
    onSendClick: (String) -> Unit,
) {
    Box(
        modifier = Modifier.fillMaxSize()
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(BackgroundGreen)
                .statusBarsPadding()
                .systemBarsPadding()
        ) {
            ChatHeader(
                modifier = Modifier.padding(32.dp),
                onBackClick = onBackClick
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
        ChatSender(
            modifier = Modifier.align(Alignment.BottomCenter),
            onSendClick = onSendClick
        )
    }
}

@Composable
private fun ChatHeader(
    modifier: Modifier,
    onBackClick: () -> Unit,
) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .padding(top = 10.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Image(
            painterResource(id = com.pepl.greenmate.core.designsystem.R.drawable.left_arrow),
            contentDescription = "뒤로 가기 버튼",
            modifier = Modifier
                .size(13.dp)
                .clickable {
                    onBackClick()
                }
        )
        Text(
            text = "페풀이",
            style = Typography.dovemayoR17,
            textAlign = TextAlign.Center
        )
        Image(
            painterResource(id = com.pepl.greenmate.core.designsystem.R.drawable.ic_menu),
            contentDescription = "추가 옵션 버튼",
            modifier = Modifier.size(19.dp)
        )
    }
}

@Composable
fun ChatSender(
    modifier: Modifier,
    onSendClick: (String) -> Unit,
) {
    var sendMessage by remember { mutableStateOf("") }

    Row(
        modifier = modifier
            .fillMaxWidth()
            .padding(start = 30.dp, end = 30.dp, top = 0.dp, bottom = 15.dp)
    ) {
        BasicTextField(
            value = sendMessage,
            onValueChange = { sendMessage = it },
            modifier = Modifier
                .fillMaxWidth()
                .shadow(
                    5.dp,
                    shape = RoundedCornerShape(24.dp),
                    spotColor = Color.Transparent
                )
                .background(White.copy(alpha = 0.8f), RoundedCornerShape(24.dp)),
            decorationBox = { innerTextField ->
                Row(
                    modifier = modifier
                        .fillMaxWidth()
                        .padding(start = 20.dp, end = 20.dp, top = 11.dp, bottom = 11.dp),
                    verticalAlignment = Alignment.CenterVertically,
                ) {
                    Box(
                        modifier = Modifier
                            .fillMaxWidth(0.9f)
                    ) {
                        innerTextField()
                        if (sendMessage.isEmpty()) {
                            Text(
                                text = "대화를 이곳에 적어주세요.",
                                color = DarkGray,
                            )
                        }
                    }
                    Image(
                        painter = painterResource(id = R.drawable.ic_send),
                        contentDescription = "채팅 보내기 버튼",
                        modifier = Modifier
                            .padding(top = 5.dp)
                            .clickable {
                                onSendClick(sendMessage)
                                sendMessage = ""
                            }
                    )

                }
            }
        )
    }

}

@Composable
private fun PlantChat(
    chat: Chat,
    isLastChat: Boolean,
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(bottom = 13.dp),
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
                    topStart = 24.dp,
                    topEnd = 24.dp,
                    bottomStart = 0.dp,
                    bottomEnd = 24.dp
                )
            } else {
                RoundedCornerShape(24.dp)
            },
            color = White,
            modifier = Modifier.weight(1F, fill = false)
        ) {
            Text(
                text = chat.message,
                style = Typography.nanumR10,
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
            style = Typography.nanumR8,
            color = DarkBrown,
        )
    }
}

@Composable
private fun UserChat(
    chat: Chat,
    isLastChat: Boolean,
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(bottom = 13.dp),
        horizontalArrangement = Arrangement.End,
        verticalAlignment = Alignment.Bottom
    ) {
        Text(
            text = chat.sendTime,
            style = Typography.nanumR8,
            color = DarkBrown,
        )
        Spacer(
            modifier = Modifier.width(5.dp)
        )
        Surface(
            shape = if (isLastChat) {
                RoundedCornerShape(
                    topStart = 24.dp,
                    topEnd = 24.dp,
                    bottomStart = 24.dp,
                    bottomEnd = 0.dp
                )
            } else {
                RoundedCornerShape(24.dp)
            },
            color = MainGreen
        ) {
            Text(
                text = chat.message,
                style = Typography.nanumR10,
                modifier = Modifier
                    .padding(
                        horizontal = 20.dp, vertical = 10.dp
                    ),
                color = White
            )
        }

    }
}

@Preview
@Composable
private fun ChatDetailPreview() {
    GreenMateTheme {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(BackgroundGreen)
        ) {
            ChatSender(
                modifier = Modifier.align(Alignment.BottomCenter),
                onSendClick = {

                }
            )
        }
    }
}