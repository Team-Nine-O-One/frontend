package com.imeanttobe.app901.api.repo

import com.imeanttobe.app901.data.model.Analysis
import com.imeanttobe.app901.data.model.SimplifiedAnalysis

interface AnalysisRepo {
    suspend fun getAllAnalyses(userId: String): Result<List<SimplifiedAnalysis>>

    suspend fun getAnalysisById(
        analysisId: Long,
        userId: String,
    ): Result<Analysis>

    suspend fun createAnalysis(
        userId: String,
        memoContents: String,
    ): Result<Analysis>

    suspend fun confirmAnalysis(
        analysisId: Long,
        userId: String,
    ): Result<Boolean>

    suspend fun updateWeights(
        analysisId: Long,
        userId: String,
        priceWeight: Double,
        distanceWeight: Double,
    ): Result<Boolean>

    suspend fun completeAnalysis(
        analysisId: Long,
        userId: String,
    ): Result<Boolean>

    suspend fun deleteAnalysis(
        analysisId: Long,
        userId: String,
    ): Result<Boolean>
}
