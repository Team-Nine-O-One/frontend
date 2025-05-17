package com.imeanttobe.app901.api.response

import com.imeanttobe.app901.data.enum.CartStatus

data class CreateCartResponse(
    val cartId: Long,
    val userId: String,
    val memoId: Long,
    val status: CartStatus,
    val createdAt: String,
)
