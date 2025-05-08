package com.imeanttobe.app901.type.data

data class RecipeIngredient(
    val id: Long, // PK
    val recipeId: Long, // FK
    val ingredientName: String
)