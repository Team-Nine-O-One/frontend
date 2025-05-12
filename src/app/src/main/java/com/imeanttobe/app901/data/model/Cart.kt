package com.imeanttobe.app901.data.model

import com.imeanttobe.app901.data.enum.CartStatus
import java.time.LocalDateTime

data class Cart(
    val cartId: Long, // PK
    val memoId: Long?, // FK - Assuming nullable based on potential 0..1 relationship or lifecycle
    val userId: String, // FK - VARCHAR user ID
    val title: String,
    val status: CartStatus, // ENUM
    val updatedAt: LocalDateTime = LocalDateTime.now(), // DATETIME
    val createdAt: LocalDateTime = LocalDateTime.now() // DATETIME
) {
    companion object {
        fun getDefaultInstance() = Cart(
            cartId = -1,
            memoId = null,
            userId = "",
            title = "Cart sample",
            status = CartStatus.IN_PROGRESS
        )
    }
}