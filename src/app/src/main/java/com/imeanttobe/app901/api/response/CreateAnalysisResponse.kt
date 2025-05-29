package com.imeanttobe.app901.api.response

import com.imeanttobe.app901.data.enum.CartStatus

data class CreateAnalysisResponse(
    val cartId: Long,
    val userId: String,
    val memoContents: String,
    val status: CartStatus,
    val createdAt: String,
)
