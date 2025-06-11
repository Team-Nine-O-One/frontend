package com.imeanttobe.app901.api.repo

import com.imeanttobe.app901.data.enum.AnalysisStatus
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
                martSummaries =
                    listOf(
                        SimplifiedMart(
                            martName = "이마트 흑석점",
                            productNames = listOf("양파", "당근", "샐러드"),
                            totalPrice = 26500,
                        ),
                        SimplifiedMart(
                            martName = "COUPANG",
                            productNames = listOf("양파", "당근", "샐러드"),
                            totalPrice = 12400,
                        ),
                    ),
                totalItems = 7,
                totalPrice = 26500 + 12400,
                isCompleted = false,
            )

        override suspend fun getAllAnalyses(
            userId: String,
            status: AnalysisStatus?,
        ): Result<List<SimplifiedAnalysis>> {
            val mockedResponse =
                listOf(
                    sampleHistory.copy(isCompleted = true, title = "홈 파티 준비"),
                    sampleHistory.copy(
                        isCompleted = true,
                        title = "내일 장 볼 것들",
                        martSummaries =
                            listOf(
                                SimplifiedMart(
                                    martName = "네이버 쇼핑",
                                    productNames = listOf("양파, 당근, 샐러드"),
                                    totalPrice = 12400,
                                ),
                                SimplifiedMart(
                                    martName = "홈플러스 용산점",
                                    productNames = listOf("양파, 당근, 샐러드"),
                                    totalPrice = 26500,
                                ),
                            ),
                    ),
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

        override suspend fun getAnalysisById(analysisId: Long): Result<Analysis> {
            val mockedResponse = Analysis.getDefaultInstance()
            delay(1000) // IO delay
            return Result.success(mockedResponse)
        }

        override suspend fun confirmAnalysis(analysisId: Long): Result<Boolean> {
            val mockedResponse = true
            return Result.success(mockedResponse)
        }

        override suspend fun completeAnalysis(analysisId: Long): Result<Boolean> {
            val mockedResponse = true
            return Result.success(mockedResponse)
        }

        override suspend fun deleteAnalysis(analysisId: Long) {
        }

        override suspend fun createAnalysis(
            userId: String,
            memoId: Long,
        ): Result<Long> {
            val mockedResponse = Analysis.getDefaultInstance()
            return Result.success(1)
        }
    }
