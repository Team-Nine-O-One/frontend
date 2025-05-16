package com.imeanttobe.app901.api.repo

interface UserRepo {
    suspend fun login(
        userId: String,
        password: String,
    ): Result<Boolean>

    suspend fun register(
        userId: String,
        password: String,
        nickname: String,
    ): Result<Boolean>

    fun isLoggedIn(): Boolean

    fun logout(): Unit

    fun getUserId(): String

    fun getNickname(): String

    suspend fun updateNickname(newNickname: String): Result<Boolean>
}
