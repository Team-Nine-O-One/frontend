package com.imeanttobe.app901.api.repo

import com.imeanttobe.app901.data.enum.GetAllCartsStatus
import com.imeanttobe.app901.data.model.Analysis
import com.imeanttobe.app901.data.model.SimplifiedAnalysis

interface AnalysisRepo {
    suspend fun getAllAnalyses(
        userId: String,
        status: GetAllCartsStatus = GetAllCartsStatus.ALL,
    ): Result<List<SimplifiedAnalysis>>

    suspend fun getAnalysisById(
        analysisId: Long,
        userId: String,
    ): Result<Analysis>

    suspend fun confirmAnalysis(
        analysisId: Long,
        userId: String,
    ): Result<Boolean>

    suspend fun completeAnalysis(
        analysisId: Long,
        userId: String,
    ): Result<Boolean>

    suspend fun deleteAnalysis(
        analysisId: Long,
        userId: String,
    ): Result<Boolean>

    suspend fun createAnalysis(
        userId: String,
        memoId: Long,
    ): Result<Long>
}
