package com.imeanttobe.app901.type

import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import java.time.format.FormatStyle
import java.util.Locale

object Formatter {
    fun dateTimeToString(dateTime: LocalDateTime): String {
        return "%d월 %d일 수정".format(dateTime.monthValue, dateTime.dayOfMonth)
    }
}