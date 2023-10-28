package com.pepl.model

data class Chat(
    val isPlantChat: Boolean,
    val imageUrl: String? = null,
    val name: String,
    val sendTime: String,
    val message: String,
)

