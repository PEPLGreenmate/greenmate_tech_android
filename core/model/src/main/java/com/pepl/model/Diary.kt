package com.pepl.model

data class Diary(
    val date: String,
    val imageUrl: String? = null,
    val title: String,
    val content: String,
)
