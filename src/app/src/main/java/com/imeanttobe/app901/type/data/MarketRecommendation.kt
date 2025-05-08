package com.imeanttobe.app901.type.data

import com.imeanttobe.app901.type.enums.MartType
import java.math.BigDecimal

data class MarketRecommendation(
    val recommendationId: Long, // PK
    val analysisId: Long, // FK
    val marketType: MartType, // ENUM
    val marketName: String,
    val totalPrice: BigDecimal // DECIMAL
)