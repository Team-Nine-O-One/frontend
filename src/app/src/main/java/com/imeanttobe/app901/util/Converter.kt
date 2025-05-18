package com.imeanttobe.app901.util

import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

object Converter {
    private val dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")

    fun getStringFromLocalDateTime(localDateTime: LocalDateTime): String =
        localDateTime.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"))

    fun getLocalDateTimeFromString(string: String): LocalDateTime = LocalDateTime.parse(string, dateTimeFormatter)
}
