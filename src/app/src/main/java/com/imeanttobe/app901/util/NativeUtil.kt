package com.imeanttobe.app901.util

import android.content.Context
import android.content.Intent
import android.net.Uri
import androidx.core.net.toUri

object NativeUtil {
    fun shareText(
        context: Context,
        textToShare: String,
        subject: String = "",
    ) {
        val sendIntent: Intent =
            Intent().apply {
                action = Intent.ACTION_SEND
                putExtra(Intent.EXTRA_TEXT, textToShare)
                type = "text/plain"
                if (subject.isNotBlank()) {
                    putExtra(Intent.EXTRA_SUBJECT, subject)
                }
            }
        val shareIntent = Intent.createChooser(sendIntent, null)
        context.startActivity(shareIntent)
    }

    fun openUrl(
        context: Context,
        url: String,
    ) {
        val intent = Intent(Intent.ACTION_VIEW)
        intent.data = url.toUri()
        context.startActivity(intent)
    }

    fun openUrl(
        context: Context,
        url: Uri,
    ) {
        val intent = Intent(Intent.ACTION_VIEW)
        intent.data = url
        context.startActivity(intent)
    }
}
