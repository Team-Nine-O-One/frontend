package com.imeanttobe.app901.data.model

import com.imeanttobe.app901.data.enum.CartStatus
import java.time.LocalDateTime

data class Cart(
    val cartId: Long, // PK
    val memoContents: String,
    val status: CartStatus, // ENUM
    val createdAt: LocalDateTime, // DATETIME
    val recommendedResults: List<RecommendationResult>,
) {
    companion object {
        fun getDefaultInstance() =
            Cart(
                cartId = -1,
                memoContents = "",
                status = CartStatus.COMPLETED,
                createdAt = LocalDateTime.now(),
                recommendedResults = emptyList(),
            )
    }
}
