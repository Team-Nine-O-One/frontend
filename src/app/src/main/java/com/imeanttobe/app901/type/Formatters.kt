package com.imeanttobe.app901.type

import android.content.Context
import com.imeanttobe.app901.R
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import java.time.format.FormatStyle
import java.util.Locale

object Formatter {
    fun dateTimeToString(dateTime: LocalDateTime, context: Context): String {
        return context.getString(R.string.format_modified_date, dateTime.monthValue, dateTime.dayOfMonth)
    }
}