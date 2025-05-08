package com.imeanttobe.app901.type.data
import com.imeanttobe.app901.type.enums.MartType
import java.math.BigDecimal
import java.time.LocalDateTime

/*
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
            name = "Default cart",
            createdAt = LocalDateTime.now(),
            updatedAt = LocalDateTime.now(),
            userId = -1,
        )
    }
}

 */

data class Cart(
    val cartId: Long, // PK
    val userId: Long, // FK
    val title: String,
    val createdAt: LocalDateTime
)

