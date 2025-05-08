package com.imeanttobe.app901.type.data

data class RecommendedItem(
    val id: Long, // PK
    val recommendationId: Long, // FK
    val itemName: String
)