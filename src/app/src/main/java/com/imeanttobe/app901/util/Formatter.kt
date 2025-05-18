package com.imeanttobe.app901.util

import android.content.Context
import com.imeanttobe.app901.BuildConfig
import com.imeanttobe.app901.R
import java.net.URLEncoder
import java.time.LocalDateTime

object Formatter {
    private const val BASE_URL = BuildConfig.API_BASE_URL

    fun getModifiedDateString(
        dateTime: LocalDateTime,
        context: Context,
    ): String = context.getString(R.string.format_modified_date, dateTime.monthValue, dateTime.dayOfMonth)

    fun encodeUrl(string: String): String = URLEncoder.encode(string, "UTF-8")
}
