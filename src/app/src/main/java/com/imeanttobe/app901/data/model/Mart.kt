package com.imeanttobe.app901.data.model

data class Mart(
    val name: String,
    val distance: Double,
    val estimatedTime: Int,
    val totalItems: Int,
    val totalPrice: Int,
    val products: List<Product>,
) {
    companion object {
        fun getDefaultInstance(): Mart =
            Mart(
                name = "이마트 흑석점",
                distance = 1.4,
                estimatedTime = 11,
                totalItems = 4,
                totalPrice = 86500,
                products =
                    listOf(
                        Product.getDefaultObject(),
                        Product.getDefaultObject(),
                        Product.getDefaultObject(),
                    ),
            )
    }
}
