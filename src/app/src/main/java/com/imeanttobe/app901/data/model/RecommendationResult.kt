package com.imeanttobe.app901.data.model

import java.math.BigDecimal

data class RecommendationResult(
    val recommendationId: Long, // PK
    val analysisId: Long, // FK
    val martId: Long, // FK
    val productId: Long, // FK
    val totalPrice: BigDecimal, // DECIMAL(10, 2)
    val pricePer100g: BigDecimal, // DECIMAL(10, 2)
    val distance: BigDecimal, // DECIMAL(10, 2)
    val deliveryFee: BigDecimal // DECIMAL(10, 2) - Note: Mart delivery_fee is BIGINT, but here it's DECIMAL. Following the ERD.
) {
    companion object {
        fun getDefaultInstance(): RecommendationResult = RecommendationResult(
            recommendationId = -1,
            analysisId = -1,
            martId = -1,
            productId = -1,
            totalPrice = BigDecimal.ZERO,
            pricePer100g = BigDecimal.ZERO,
            distance = BigDecimal.ZERO,
            deliveryFee = BigDecimal.ZERO
        )
    }
}
