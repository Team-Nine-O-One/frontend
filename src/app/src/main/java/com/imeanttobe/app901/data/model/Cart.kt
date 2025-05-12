package com.imeanttobe.app901.data.model

import java.time.LocalDateTime

data class Cart(
    val id: Int,
    val name: String,
    val createdAt: LocalDateTime,
    val updatedAt: LocalDateTime,
    val userId: Int,
) {
    companion object {
        fun getDefaultInstance() = Cart(
            id = -1,
            name = "홈 파티 장바구니",
            createdAt = LocalDateTime.now(),
            updatedAt = LocalDateTime.now(),
            userId = -1,
        )
    }
}