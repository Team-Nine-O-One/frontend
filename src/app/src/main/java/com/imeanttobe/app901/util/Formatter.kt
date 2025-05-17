package com.imeanttobe.app901.util

import android.content.Context
import com.imeanttobe.app901.R
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

object Formatter {
    private val dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")

    fun getModifiedDateString(
        dateTime: LocalDateTime,
        context: Context,
    ): String = context.getString(R.string.format_modified_date, dateTime.monthValue, dateTime.dayOfMonth)

    fun getStringFromLocalDateTime(localDateTime: LocalDateTime): String =
        localDateTime.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"))

    fun getLocalDateTimeFromString(string: String): LocalDateTime = LocalDateTime.parse(string, dateTimeFormatter)
}
