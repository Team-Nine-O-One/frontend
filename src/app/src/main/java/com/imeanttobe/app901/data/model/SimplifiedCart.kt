package com.imeanttobe.app901.data.model

import com.imeanttobe.app901.data.enum.CartStatus
import java.time.LocalDateTime

data class SimplifiedCart(
    val cartId: Long,
    val memoId: Long,
    val status: CartStatus,
    val createdAt: LocalDateTime,
)
