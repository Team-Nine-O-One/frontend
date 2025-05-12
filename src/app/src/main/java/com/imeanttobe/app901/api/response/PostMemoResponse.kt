package com.imeanttobe.app901.api.response

import java.time.LocalDateTime

data class PostMemoResponse(
    val memoId: Long,
    val userId: String,
    val contents: String,
    val createdAt: LocalDateTime,
)