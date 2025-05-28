package com.imeanttobe.app901.data.model

data class SimplifiedHistory(
    val cartId: Long,
    val title: String,
    val marts: List<SimplifiedMart>,
    val totalItems: Int,
    val totalPrice: Int,
) {
    companion object {
        fun getDefaultInstance() =
            SimplifiedHistory(
                cartId = 0,
                title = "",
                marts = emptyList(),
                totalItems = 0,
                totalPrice = 0,
            )
    }
}
