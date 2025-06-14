package com.imeanttobe.app901.api.repo

interface RemoteMemoRepo {
    suspend fun createMemo(
        userId: String,
        rawText: String,
        userLat: Double,
        userLng: Double,
    ): Result<Long>
}
