package com.imeanttobe.app901.data.model

data class Store(
    val name: String,
    val distance: Double,
    val estimatedTime: Int,
    val totalItems: Int,
    val totalPrice: Int,
    val link: String,
    val products: List<Product>,
) {
    companion object {
        fun getDefaultInstance(): Store =
            Store(
                name = "이마트 흑석점",
                distance = 1.4,
                estimatedTime = 11,
                totalItems = 4,
                totalPrice = 86500,
                link = "https://www.naver.com",
                products =
                    listOf(
                        Product.getDefaultObject(),
                        Product.getDefaultObject(),
                        Product.getDefaultObject(),
                    ),
            )
    }
}
