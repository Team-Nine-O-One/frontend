package com.imeanttobe.app901.type.data

import java.time.LocalDateTime

data class User(
    val userId: Long, // PK
    val username: String,
    val email: String,
    val createdAt: LocalDateTime
)