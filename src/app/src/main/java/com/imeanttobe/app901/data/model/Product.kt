package com.imeanttobe.app901.data.model

data class Product(
    val productId: Long, // PK
    val name: String,
    // TODO: null 대응
    val imageUrl: String // VARCHAR(255) - Assuming nullable
) {
    companion object {
        fun getDefaultObject(): Product = Product(
            productId = 0,
            name = "Sample product",
            imageUrl = ""
        )
    }
}
