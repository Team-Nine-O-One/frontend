package com.imeanttobe.app901.api.response

import com.imeanttobe.app901.data.enum.CartStatus
import com.imeanttobe.app901.data.model.RecommendationResult

data class GetCartByIdResponse(
    val cartId: Long,
    val memoContents: String,
    val status: CartStatus,
    val createdAt: String,
    val recommendedResult: List<RecommendationResult>,
)
