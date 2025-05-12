package com.imeanttobe.app901.data.model

import java.math.BigDecimal

data class CartItem(
    val cartItemsId: Long, // PK - Note: attribute name is cart_items_id in ERD
    val martProductPriceId: Long, // FK
    val cartId: Long, // FK
    val quantity: BigDecimal // DECIMAL(10, 2)
) {
    companion object {
        fun getDefaultObject(): CartItem = CartItem(
            cartItemsId = 0,
            martProductPriceId = 0,
            cartId = 0,
            quantity = BigDecimal.ZERO
        )
    }
}
