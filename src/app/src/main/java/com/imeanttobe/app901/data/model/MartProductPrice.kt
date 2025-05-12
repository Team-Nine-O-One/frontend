package com.imeanttobe.app901.data.model

import com.imeanttobe.app901.data.enum.ProductUnit
import java.math.BigDecimal

data class MartProductPrice(
    val martProductPriceId: Long, // PK
    val martId: Long, // FK
    val productId: Long, // FK
    val price: BigDecimal, // DECIMAL(10, 2)
    val weight: BigDecimal, // DECIMAL(10, 2)
    val unit: ProductUnit, // ENUM
    val pricePer100g: BigDecimal // DECIMAL(10, 2)
) {
    companion object {
        fun getDefaultInstance(): MartProductPrice = MartProductPrice(
            martProductPriceId = -1,
            martId = -1,
            productId = -1,
            price = BigDecimal.ZERO,
            weight = BigDecimal.ZERO,
            unit = ProductUnit.KG,
            pricePer100g = BigDecimal.ZERO
        )
    }
}
