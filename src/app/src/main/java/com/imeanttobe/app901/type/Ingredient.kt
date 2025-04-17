package com.imeanttobe.app901.type

data class Ingredient(
    val id: Int,
    val name: String,
    val category: String,
    val imageUrl: String,
    val martId: Int
) {
    companion object {
        fun getDefaultInstance(): Ingredient {
            return Ingredient(
                id = -1,
                name = "토마토",
                category = "",
                imageUrl = "",
                martId = -1
            )
        }
    }
}
