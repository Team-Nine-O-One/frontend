package com.imeanttobe.app901.data.model

data class Store(
    val name: String,
    val distance: Double? = null,
    val estimatedTime: Int? = null,
    val totalItems: Int,
    val totalPrice: Int,
    val isOnline: Boolean,
    val link: String? = null,
    val pos: LatAndLng? = null,
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
                link = null,
                pos = LatAndLng(lat = 37.506791, lng = 126.958051),
                isOnline = false,
                products =
                    listOf(
                        Product.getDefaultObject(),
                        Product.getDefaultObject(),
                        Product.getDefaultObject(),
                    ),
            )
    }
}
