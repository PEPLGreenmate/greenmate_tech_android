package com.pepl.chat

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
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
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.pepl.designsystem.theme.BLACK
import com.pepl.designsystem.theme.GreenMateTheme
import com.pepl.designsystem.theme.MainGreen
import com.pepl.designsystem.theme.Typography
import com.pepl.model.ChatRoom
import com.pepl.ui.LoadingScreen
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
                LoadingScreen()
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
    viewModel: ChatViewModel = hiltViewModel(),
) {
    var showDialog by remember { mutableStateOf(false) }

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

        Image(
            painterResource(id = com.pepl.greenmate.core.designsystem.R.drawable.ic_menu),
            contentDescription = "추가 옵션 버튼",
            modifier = Modifier
                .size(19.dp)
                .clickable {
                    showDialog = true
                }
                .align(Alignment.CenterEnd),
        )

        Column(
            modifier = Modifier.fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(14.dp)
        ) {
            Text(
                text = "채팅",
                style = Typography.dovemayoR18,
                color = BLACK,
            )
            Text(
                text = "나의 식물과 오늘 하루를 공유하세요",
                style = Typography.suitL11,
                color = Color.Gray
            )
        }

        if (showDialog) {

            Dialog(
                onDismissRequest = {
                    showDialog = false
                },
                properties = DialogProperties(
                    dismissOnBackPress = true,
                    dismissOnClickOutside = true
                ),
                content = {
                    Box(
                        modifier = Modifier
                            .shadow(
                                elevation = 100.dp,
                                spotColor = Color(0x40000000),
                                ambientColor = Color(0x40000000)
                            )
                            .background(color = MainGreen, shape = RoundedCornerShape(size = 20.dp))
                            .padding(16.dp)
                            .clickable {
                                viewModel.sendDiary()
                            }
                    ) {
                        Text(
                            text = "일기작성하기",
                            style = Typography.dovemayoR19,
                            color = Color.White,
                        )
                    }
                }
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
