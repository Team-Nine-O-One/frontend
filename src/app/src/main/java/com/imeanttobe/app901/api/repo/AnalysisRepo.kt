package com.imeanttobe.app901.api.repo

import com.imeanttobe.app901.data.model.Analysis
import com.imeanttobe.app901.data.model.SimplifiedAnalysis

interface AnalysisRepo {
    suspend fun getAllAnalyses(userId: String): Result<List<SimplifiedAnalysis>>

    suspend fun getAnalysisById(
        cartId: Long,
        userId: String,
    ): Result<Analysis>

    suspend fun createAnalysis(
        userId: String,
        memoContents: String,
    ): Result<Analysis>

    suspend fun confirmAnalysis(
        cartId: Long,
        userId: String,
    ): Result<Boolean>

    suspend fun updateWeights(
        cartId: Long,
        userId: String,
        priceWeight: Double,
        distanceWeight: Double,
    ): Result<Boolean>

    suspend fun completeAnalysis(
        cartId: Long,
        userId: String,
    ): Result<Boolean>

    suspend fun deleteAnalysis(
        cartId: Long,
        userId: String,
    ): Result<Boolean>
}
