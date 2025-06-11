package com.imeanttobe.app901.api.body

data class CreateRemoteMemoRequest(
    val userId: String,
    val rawText: String,
    val userLat: Double,
    val userLng: Double,
)
