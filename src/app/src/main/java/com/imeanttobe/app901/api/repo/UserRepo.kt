package com.imeanttobe.app901.api.repo

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

    fun logout(): Unit

    fun getUserId(): String

    fun getNickname(): String

    suspend fun updateNickname(newNickname: String): Result<Boolean>
}
