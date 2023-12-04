package com.pepl.util

import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

private const val growStartDateFormat = "yyyy-MM-dd"
private const val dateTimeFormat = "yyyy-MM-dd hh-mm-ss"
private const val chatDateFormat = "HH:mm"
private const val dateFormat = "yy년 MM월 dd일 EEEE"
private val simpleDateFormat = SimpleDateFormat(dateTimeFormat, Locale.KOREA)
private val simpleGrowStartDateFormat = SimpleDateFormat(growStartDateFormat, Locale.KOREA)
private val simpleChatDateFormat = SimpleDateFormat(chatDateFormat, Locale.KOREA)
private val simpleDate = SimpleDateFormat(dateFormat,Locale.KOREA)

fun String.toLongTime(): Long = simpleDateFormat.parse(this).time

fun String.toStartDateLongTime(): Long = simpleGrowStartDateFormat.parse(this).time

fun Long.toString(): String = simpleDateFormat.format(this)


fun Long.toChatDateString(): String = simpleChatDateFormat.format(this)

fun Date.toDate(): String = simpleDate.format(this)

fun getDiffHour(last: Long): Int {
    val current = getCurrentLongTime()

    return ((current - last) / (1000 * 60 * 60)).toInt()
}

fun getDiffDate(start: Long): Int {
    val current = getCurrentLongTime()

    return ((current - start) / (1000 * 60 * 60)).toInt()
}

fun getCurrentLongTime() = Date(java.lang.System.currentTimeMillis()).time
fun getCurrentLongDate() = Date(java.lang.System.currentTimeMillis())