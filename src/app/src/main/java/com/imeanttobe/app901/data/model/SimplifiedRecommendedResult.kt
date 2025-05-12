package com.imeanttobe.app901.data.model

import java.math.BigDecimal

data class SimplifiedRecommendedResult(
    val marketName: String,
    val productName: String,
    val price: BigDecimal,
    val distance: BigDecimal,
    val deliveryFee: BigDecimal,
)
