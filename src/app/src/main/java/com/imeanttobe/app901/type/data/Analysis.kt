package com.imeanttobe.app901.type.data

import java.time.LocalDateTime

data class Analysis(
    val analysisId: Long, // PK
    val cartId: Long, // FK
    val createdAt: LocalDateTime
)