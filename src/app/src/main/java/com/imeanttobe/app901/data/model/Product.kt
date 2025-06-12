package com.imeanttobe.app901.data.model

import com.google.gson.annotations.SerializedName

data class Product(
    @SerializedName("product") val name: String,
    val price: Int,
    val pricePer100g: Double,
) {
    companion object {
        fun getDefaultObject(): Product =
            Product(
                name = "우유",
                price = 1000,
                pricePer100g = 200.0,
            )
    }
}
