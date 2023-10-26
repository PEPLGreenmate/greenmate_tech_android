package com.pepl.data.repository

import com.pepl.data.api.GreenmateApi
import com.pepl.model.Chat
import com.pepl.model.ChatRoom
import javax.inject.Inject

class DefaultChatRepository @Inject constructor(
    private val greenmateApi: GreenmateApi,
) : ChatRepository {
    override suspend fun getChatRooms(): List<ChatRoom> {
        return listOf(
            ChatRoom(
                "",
                "https://unsplash.com/ko/%EC%82%AC%EC%A7%84/%EA%B0%88%EC%83%89-%EB%82%98%EB%AC%B4-%EA%BD%83%EB%B3%91%EC%97%90-%EB%85%B9%EC%83%89-%EC%8B%9D%EB%AC%BC-Ebwp2-6BG8E",
                "페풀이",
                true,
                "water",
                "나 목말라, 물이 필요해!!",
                "08:30",
                false
            ),
            ChatRoom(
                "",
                "https://unsplash.com/ko/%EC%82%AC%EC%A7%84/grune-kaktuspflanze-auf-braunem-topf-fbAnIjhrOL4",
                "그린메이트",
                false,
                "water",
                "오늘 하루 화이팅이에요!",
                "08:25",
                true
            ),
            ChatRoom(
                "",
                "https://unsplash.com/ko/%EC%82%AC%EC%A7%84/%EA%B0%88%EC%83%89-%EB%82%98%EB%AC%B4-%EA%BD%83%EB%B3%91%EC%97%90-%EB%85%B9%EC%83%89-%EC%8B%9D%EB%AC%BC-Ebwp2-6BG8E",
                "선인장",
                true,
                "water",
                "나 목말라, 물이 필요해!!",
                "08:30",
                false
            ),
            ChatRoom(
                "",
                "https://unsplash.com/ko/%EC%82%AC%EC%A7%84/%EA%B0%88%EC%83%89-%EB%82%98%EB%AC%B4-%EA%BD%83%EB%B3%91%EC%97%90-%EB%85%B9%EC%83%89-%EC%8B%9D%EB%AC%BC-Ebwp2-6BG8E",
                "그린조아",
                true,
                "water",
                "오늘 하루 화이팅이에요!",
                "08:30",
                false
            ),
            ChatRoom(
                "",
                "https://unsplash.com/ko/%EC%82%AC%EC%A7%84/%EA%B0%88%EC%83%89-%EB%82%98%EB%AC%B4-%EA%BD%83%EB%B3%91%EC%97%90-%EB%85%B9%EC%83%89-%EC%8B%9D%EB%AC%BC-Ebwp2-6BG8E",
                "플래그먼트",
                true,
                "water",
                "나 목말라, 물이 필요해!!",
                "08:10",
                true
            )
        )
    }

    override suspend fun getChats(roomId: String): List<Chat> {
        return listOf(
            Chat(
                true,
                "https://unsplash.com/ko/%EC%82%AC%EC%A7%84/%EA%B0%88%EC%83%89-%EB%82%98%EB%AC%B4-%EA%BD%83%EB%B3%91%EC%97%90-%EB%85%B9%EC%83%89-%EC%8B%9D%EB%AC%BC-Ebwp2-6BG8E",
                "페풀이",
                "08:30",
                "나 목말라. 물이 필요해!"
            ),
            Chat(
                true,
                "https://unsplash.com/ko/%EC%82%AC%EC%A7%84/%EA%B0%88%EC%83%89-%EB%82%98%EB%AC%B4-%EA%BD%83%EB%B3%91%EC%97%90-%EB%85%B9%EC%83%89-%EC%8B%9D%EB%AC%BC-Ebwp2-6BG8E",
                "페풀이",
                "08:35",
                "물 줘서 고마워. 덕분에 행복해졌어!"
            ),
            Chat(
                false,
                "https://unsplash.com/ko/%EC%82%AC%EC%A7%84/%EA%B0%88%EC%83%89-%EB%82%98%EB%AC%B4-%EA%BD%83%EB%B3%91%EC%97%90-%EB%85%B9%EC%83%89-%EC%8B%9D%EB%AC%BC-Ebwp2-6BG8E",
                "페풀이",
                "08:40",
                "다음에도 도움 필요하면 언제든 말해"
            ),
            Chat(
                true,
                "https://unsplash.com/ko/%EC%82%AC%EC%A7%84/%EA%B0%88%EC%83%89-%EB%82%98%EB%AC%B4-%EA%BD%83%EB%B3%91%EC%97%90-%EB%85%B9%EC%83%89-%EC%8B%9D%EB%AC%BC-Ebwp2-6BG8E",
                "페풀이",
                "08:41",
                "오늘 회사 잘 다녀와!"
            ),
            Chat(
                false,
                "https://unsplash.com/ko/%EC%82%AC%EC%A7%84/%EA%B0%88%EC%83%89-%EB%82%98%EB%AC%B4-%EA%BD%83%EB%B3%91%EC%97%90-%EB%85%B9%EC%83%89-%EC%8B%9D%EB%AC%BC-Ebwp2-6BG8E",
                "페풀이",
                "20:30",
                "오늘 회사에서 힘든 일이 있었어"
            ),
            Chat(
                true,
                "https://unsplash.com/ko/%EC%82%AC%EC%A7%84/%EA%B0%88%EC%83%89-%EB%82%98%EB%AC%B4-%EA%BD%83%EB%B3%91%EC%97%90-%EB%85%B9%EC%83%89-%EC%8B%9D%EB%AC%BC-Ebwp2-6BG8E",
                "페풀이",
                "20:31",
                "누구야! 누가 괴롭혔어!"
            )
        )
    }
}