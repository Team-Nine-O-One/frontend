package com.imeanttobe.app901.type.data

data class CartItem(
    val itemId: Long, // PK
    val cartId: Long, // FK
    val ingredientName: String
)
