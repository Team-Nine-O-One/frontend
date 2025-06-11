package com.imeanttobe.app901.api.repo

import com.imeanttobe.app901.data.enum.AnalysisStatus
import com.imeanttobe.app901.data.model.Analysis
import com.imeanttobe.app901.data.model.SimplifiedAnalysis

interface AnalysisRepo {
    suspend fun getAllAnalyses(
        userId: String,
        status: AnalysisStatus? = null,
    ): Result<List<SimplifiedAnalysis>>

    suspend fun getAnalysisById(analysisId: Long): Result<Analysis>

    suspend fun confirmAnalysis(analysisId: Long): Result<Boolean>

    suspend fun completeAnalysis(analysisId: Long): Result<Boolean>

    suspend fun deleteAnalysis(analysisId: Long)

    suspend fun createAnalysis(
        userId: String,
        memoId: Long,
    ): Result<Long>
}
