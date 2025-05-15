package com.imeanttobe.app901.data.model

import java.math.BigDecimal

data class RecommendationResult(
    val martName: String,
    val productName: String,
    val price: BigDecimal,
    val distance: Double,
    val deliveryFee: BigDecimal,
) {
    companion object {
        fun getDefaultInstance(): RecommendationResult =
            RecommendationResult(
                martName = "Mart sample",
                productName = "Product sample",
                price = BigDecimal.ZERO,
                distance = 0.0,
                deliveryFee = BigDecimal.ZERO,
            )
    }
}
