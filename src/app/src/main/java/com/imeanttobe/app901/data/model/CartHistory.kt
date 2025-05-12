package com.imeanttobe.app901.data.model

import java.math.BigDecimal
import java.time.LocalDateTime

data class CartHistory(
    val historyId: Long, // PK
    val recommendationId: Long?, // FK - Assuming nullable based on potential 0..1 relationship or lifecycle
    val userId: String, // VARCHAR user ID
    val totalPrice: BigDecimal, // DECIMAL
    val createdAt: LocalDateTime = LocalDateTime.now(), // TIMESTAMP
    // TODO: 나중에 API 요청 시 null일 때 문자열 채워 보내야 함
    val marketSummary: String, // VARCHAR - Assuming nullable
    val marketImages: String // JSON stored as String - Assuming nullable
) {
    companion object {
        fun getDefaultInstance(): CartHistory = CartHistory(
            historyId = -1,
            recommendationId = null,
            userId = "",
            totalPrice = BigDecimal.ZERO,
            marketSummary = "",
            marketImages = ""
        )
    }
}
