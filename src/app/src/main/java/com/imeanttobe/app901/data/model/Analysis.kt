package com.imeanttobe.app901.data.model

import java.math.BigDecimal
import java.time.LocalDateTime

data class Analysis(
    val analysisId: Long, // PK
    val cartId: Long, // FK
    val userId: String, // VARCHAR user ID
    val createdAt: LocalDateTime = LocalDateTime.now(), // TIMESTAMP
    val priceWeight: BigDecimal, // DECIMAL(5, 2)
    val distanceWeight: BigDecimal, // DECIMAL(5, 2)
    val isConfirmed: Boolean, // BOOLEAN
    val userLatitude: Double, // DOUBLE
    val userLongitude: Double, // DOUBLE
    val field: String // VARCHAR
) {
    companion object {
        fun getDefaultInstance(): Analysis = Analysis(
            analysisId = -1,
            cartId = -1,
            userId = "",
            priceWeight = BigDecimal.ZERO,
            distanceWeight = BigDecimal.ZERO,
            isConfirmed = false,
            userLatitude = 0.0,
            userLongitude = 0.0,
            field = "Sample analysis"
        )
    }
}
