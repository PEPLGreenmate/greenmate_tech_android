package com.pepl.util

import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

private const val dateFormat = "yyyy-MM-dd HH:mm:ss"
private const val chatDateFormat = "HH:mm"
private val simpleDateFormat = SimpleDateFormat(dateFormat, Locale.KOREA)
private val simpleChatDateFormat = SimpleDateFormat(chatDateFormat, Locale.KOREA)

fun String.toLongTime(): Long = simpleDateFormat.parse(this).time

fun Long.toString(): String = simpleDateFormat.format(this)

fun Long.toChatDateString(): String = simpleChatDateFormat.format(this)

fun getDiffHour(last: Long): Int {
    val current = getCurrentLongTime()

    return ((current - last) / (1000 * 60 * 60)).toInt()
}

fun getCurrentLongTime() = Date(java.lang.System.currentTimeMillis()).time