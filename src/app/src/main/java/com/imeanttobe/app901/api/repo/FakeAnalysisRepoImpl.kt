package com.imeanttobe.app901.api.repo

import com.imeanttobe.app901.data.model.Analysis
import com.imeanttobe.app901.data.model.SimplifiedAnalysis
import com.imeanttobe.app901.data.model.SimplifiedMart
import kotlinx.coroutines.delay
import javax.inject.Inject

class FakeAnalysisRepoImpl
    @Inject
    constructor() : AnalysisRepo {
        private val sampleHistory =
            SimplifiedAnalysis(
                cartId = 4,
                title = "8월 17일 수요일 12시 30분",
                marts =
                    listOf(
                        SimplifiedMart(
                            martName = "이마트 흑석점",
                            displayName = "양파 외 3건",
                            totalPrice = 26500,
                        ),
                        SimplifiedMart(
                            martName = "COUPANG",
                            displayName = "백산수 외 2건",
                            totalPrice = 12400,
                        ),
                    ),
                totalItems = 7,
                totalPrice = 26500 + 12400,
                isCompleted = false,
            )

        override suspend fun getAllAnalyses(userId: String): Result<List<SimplifiedAnalysis>> {
            val mockedResponse =
                listOf(
                    sampleHistory.copy(isCompleted = true),
                    sampleHistory.copy(isCompleted = true),
                    sampleHistory.copy(isCompleted = true),
                    sampleHistory.copy(isCompleted = true),
                    sampleHistory.copy(isCompleted = true),
                    sampleHistory.copy(isCompleted = false),
                    sampleHistory.copy(isCompleted = false),
                    sampleHistory.copy(isCompleted = false),
                    sampleHistory.copy(isCompleted = false),
                    sampleHistory.copy(isCompleted = false),
                )
            delay(300) // IO delay
            return Result.success(mockedResponse)
        }

        override suspend fun getAnalysisById(
            analysisId: Long,
            userId: String,
        ): Result<Analysis> {
            val mockedResponse = Analysis.getDefaultInstance()
            delay(1000) // IO delay
            return Result.success(mockedResponse)
        }

        override suspend fun createAnalysis(
            userId: String,
            memoContents: String,
        ): Result<Analysis> {
            val mockedResponse = Analysis.getDefaultInstance()
            return Result.success(mockedResponse)
        }

        override suspend fun confirmAnalysis(
            analysisId: Long,
            userId: String,
        ): Result<Boolean> {
            val mockedResponse = true
            return Result.success(mockedResponse)
        }

        override suspend fun updateWeights(
            analysisId: Long,
            userId: String,
            priceWeight: Double,
            distanceWeight: Double,
        ): Result<Boolean> {
            val mockedResponse = true
            return Result.success(mockedResponse)
        }

        override suspend fun completeAnalysis(
            analysisId: Long,
            userId: String,
        ): Result<Boolean> {
            val mockedResponse = true
            return Result.success(mockedResponse)
        }

        override suspend fun deleteAnalysis(
            analysisId: Long,
            userId: String,
        ): Result<Boolean> {
            val mockedResponse = true
            return Result.success(mockedResponse)
        }
    }
