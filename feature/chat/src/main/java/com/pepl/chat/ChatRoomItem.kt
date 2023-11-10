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
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.painter.ColorPainter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.pepl.designsystem.component.NetworkImage
import com.pepl.designsystem.theme.BLACK
import com.pepl.designsystem.theme.DarkBrown
import com.pepl.designsystem.theme.Gray
import com.pepl.designsystem.theme.GreenMateTheme
import com.pepl.designsystem.theme.MainGreen
import com.pepl.designsystem.theme.Typography
import com.pepl.designsystem.theme.White
import com.pepl.greenmate.feature.chat.R
import com.pepl.model.ChatRoom

@Composable
fun ChatRoomItem(
    onChatRoomClick: (ChatRoom) -> Unit = {},
    chatRoom: ChatRoom,
) {
    Surface(
        onClick = { onChatRoomClick(chatRoom) }
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .background(White)
                .padding(
                    PaddingValues(start = 28.dp, top = 18.dp, end = 28.dp, bottom = 18.dp)
                )
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically
            ) {
                NetworkImage(
                    imageUrl = chatRoom.plantImageUrl,
                    placeholder = ColorPainter(Gray),
                    modifier = Modifier
                        .size(40.dp)
                        .clip(CircleShape)
                        .background(Gray)
                        .clickable(onClick = {})
                )
                Spacer(
                    modifier = Modifier.width(13.dp)
                )
                ChatRecent(
                    modifier = Modifier
                        .width(0.dp)
                        .weight(1F),
                    chatRoom = chatRoom
                )
            }

            if (chatRoom.isLacked) {
                ChatLackImage(
                    modifier = Modifier
                        .align(Alignment.TopStart),
                    chatRoom.lackedAttribute
                )
            }
        }


    }
}

@Composable
private fun ChatRecent(
    modifier: Modifier = Modifier,
    chatRoom: ChatRoom,
) {
    Column(
        modifier = modifier
            .fillMaxWidth(),
        verticalArrangement = Arrangement.spacedBy(space = 8.dp)
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.Bottom
        ) {
            Text(
                modifier = Modifier.weight(1F),
                text = chatRoom.plantName,
                style = Typography.nanumR13,
                color = BLACK
            )
            Text(
                text = chatRoom.lastSendTime,
                style = Typography.nanumL10,
                color = DarkBrown
            )
        }
        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.Bottom
        ) {
            Text(
                modifier = Modifier.weight(1F),
                text = chatRoom.lastMessage,
                style = Typography.nanumL10,
                color = DarkBrown
            )
            if (chatRoom.isRead.not()) {
                Box(
                    modifier = Modifier
                        .background(color = MainGreen, shape = CircleShape)
                        .size(9.dp)
                )
            }

        }
    }
}

@Composable
private fun ChatLackImage(
    modifier: Modifier = Modifier,
    lackedAttribute: String,
) {
    return when (lackedAttribute) {
        "water" -> {
            Image(
                painter = painterResource(id = R.drawable.ic_water),
                contentDescription = null,
                modifier = modifier
                    .size(
                        width = 16.dp,
                        height = 19.dp
                    )
            )
        }

        "light" -> {
            Image(
                painter = painterResource(id = R.drawable.ic_water),
                contentDescription = null,
                modifier = modifier
                    .size(
                        width = 16.dp,
                        height = 19.dp
                    )
            )
        }

        "temp" -> {
            Image(
                painter = painterResource(id = R.drawable.ic_water),
                contentDescription = null,
                modifier = modifier
                    .size(
                        width = 16.dp,
                        height = 19.dp
                    )
            )
        }

        "humidity" -> {
            Image(
                painter = painterResource(id = R.drawable.ic_water),
                contentDescription = null,
                modifier = modifier
                    .size(
                        width = 16.dp,
                        height = 19.dp
                    )
            )
        }

        else -> {

        }
    }
}


@Composable
private fun ChatRoomItemPreview() {
    GreenMateTheme {
        Box {
            ChatRoomItem(
                chatRoom = ChatRoom.createEmpty(),
            )
        }
    }
}

