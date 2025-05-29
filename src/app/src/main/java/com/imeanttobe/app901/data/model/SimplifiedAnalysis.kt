package com.imeanttobe.app901.data.model

import kotlin.random.Random

data class SimplifiedAnalysis(
    val cartId: Long,
    val title: String,
    val marts: List<SimplifiedMart>,
    val totalItems: Int,
    val totalPrice: Int,
    val isCompleted: Boolean,
) {
    companion object {
        fun getDefaultInstance() =
            SimplifiedAnalysis(
                cartId = 0,
                title = "",
                marts = emptyList(),
                totalItems = 0,
                totalPrice = 0,
                isCompleted = Random.nextBoolean(),
            )
    }
}
