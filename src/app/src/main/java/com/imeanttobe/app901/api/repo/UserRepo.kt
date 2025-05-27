package com.imeanttobe.app901.api.repo

import kotlinx.coroutines.flow.Flow

interface UserRepo {
    suspend fun login(
        email: String,
        password: String,
    ): Result<Boolean>

    suspend fun register(
        nickname: String,
        email: String,
        password: String,
    ): Result<Boolean>

    fun isLoggedIn(): Boolean

    fun logout()

    fun getUserId(): String

    // fun getNickname(): String
    val getNicknameFlow: Flow<String>

    suspend fun updateNickname(newValue: String): Result<Boolean>

    suspend fun updatePassword(newValue: String): Result<Boolean>
}
