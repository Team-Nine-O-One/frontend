package com.imeanttobe.app901.data.model

import com.imeanttobe.app901.data.enum.CartStatus
import java.time.LocalDateTime

data class Cart(
    val cartId: Long, // PK
    val memoId: Long?, // FK - Assuming nullable based on potential 0..1 relationship or lifecycle
    val status: CartStatus, // ENUM
    val createdAt: LocalDateTime, // DATETIME
    val recommendedResults: List<RecommendationResult>,
) {
    companion object {
        fun getDefaultInstance() =
            Cart(
                cartId = -1,
                memoId = null,
                status = CartStatus.COMPLETED,
                createdAt = LocalDateTime.now(),
                recommendedResults = emptyList(),
            )
    }
}
