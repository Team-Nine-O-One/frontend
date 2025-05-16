package com.imeanttobe.app901.util

import android.util.Patterns

object Verifier {
    fun isValidEmail(value: String): Boolean = value.isNotEmpty() && Patterns.EMAIL_ADDRESS.matcher(value).matches()

    fun isValidPassword(value: String): Boolean = value.isNotEmpty() && value.length >= 6

    fun isValidNickname(value: String): Boolean = value.isNotEmpty() && value.length <= 16
}
