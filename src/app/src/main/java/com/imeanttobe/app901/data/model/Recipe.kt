package com.imeanttobe.app901.data.model

data class Recipe(
    val recipeId: Long, // PK
    val name: String,
    val ingredients: String // TEXT
) {
    companion object {
        fun getDefaultInstance(): Recipe {
            return Recipe(
                recipeId = -1,
                name = "Sample Recipe",
                ingredients = "Sample Ingredients"
            )
        }
    }
}
