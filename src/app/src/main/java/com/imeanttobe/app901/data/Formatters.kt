package com.imeanttobe.app901.data

import android.content.Context
import com.imeanttobe.app901.R
import java.time.LocalDateTime

object Formatter {
    fun dateTimeToString(dateTime: LocalDateTime, context: Context): String {
        return context.getString(R.string.format_modified_date, dateTime.monthValue, dateTime.dayOfMonth)
    }
}