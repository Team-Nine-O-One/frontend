package com.imeanttobe.app901.api.repo

import com.imeanttobe.app901.api.body.CreateCartRequest
import com.imeanttobe.app901.api.service.AnalysisService
import com.imeanttobe.app901.data.enum.GetAllCartsStatus
import com.imeanttobe.app901.data.model.Analysis
import com.imeanttobe.app901.data.model.SimplifiedAnalysis
import javax.inject.Inject

class AnalysisRepoImpl
    @Inject
    constructor(
        private val analysisService: AnalysisService,
    ) : AnalysisRepo {
        override suspend fun getAllAnalyses(
            userId: String,
            status: GetAllCartsStatus,
        ): Result<List<SimplifiedAnalysis>> {
            val response =
                analysisService.getAllCarts(
                    userId = userId,
                    status = status,
                )

            return if (response.isSuccessful) {
                val body = response.body()
                if (body != null) {
                    Result.success(body)
                } else {
                    Result.failure(Exception("Response body is null"))
                }
            } else {
                if (response.code() == 400) {
                    Result.success(emptyList())
                } else {
                    Result.failure(Exception("Failed to fetch carts"))
                }
            }
        }

        override suspend fun getAnalysisById(
            analysisId: Long,
            userId: String,
        ): Result<Analysis> {
            val response = analysisService.getCartById(cartId = analysisId)

            return if (response.isSuccessful) {
                val body = response.body()
                if (body != null) {
                    Result.success(
                        Analysis.getDefaultInstance(),
                    )
                } else {
                    Result.failure(Exception("Response body is null"))
                }
            } else {
                Result.failure(Exception("Failed to fetch cart"))
            }
        }

        override suspend fun confirmAnalysis(
            analysisId: Long,
            userId: String,
        ): Result<Boolean> {
            val response = analysisService.confirmCart(cartId = analysisId, userId = userId)

            return if (response.isSuccessful) {
                val body = response.body()
                if (body != null) {
                    Result.success(true)
                } else {
                    Result.failure(Exception("Response body is null"))
                }
            } else {
                Result.failure(Exception("Failed to confirm cart"))
            }
        }

        override suspend fun completeAnalysis(
            analysisId: Long,
            userId: String,
        ): Result<Boolean> {
            val response = analysisService.completeCart(cartId = analysisId, userId = userId)
            return if (response.isSuccessful) {
                val body = response.body()
                if (body != null) {
                    Result.success(true)
                } else {
                    Result.failure(Exception("Response body is null"))
                }
            } else {
                Result.failure(Exception("Failed to complete cart"))
            }
        }

        override suspend fun deleteAnalysis(
            analysisId: Long,
            userId: String,
        ): Result<Boolean> {
            val response = analysisService.deleteCart(cartId = analysisId, userId = userId)

            return if (response.isSuccessful) {
                val body = response.body()
                if (body != null) {
                    Result.success(true)
                } else {
                    Result.failure(Exception("Response body is null"))
                }
            } else {
                Result.failure(Exception("Failed to delete cart"))
            }
        }

        override suspend fun createAnalysis(
            userId: String,
            memoId: Long,
        ): Result<Long> {
            val postResponse =
                analysisService.createCart(
                    request =
                        CreateCartRequest(
                            userId = userId,
                            memoId = memoId,
                        ),
                )
            return if (postResponse.isSuccessful) {
                val body = postResponse.body()
                if (body != null) {
                    Result.success(body.cartId)
                } else {
                    Result.failure(Exception("Response body is null"))
                }
            } else {
                Result.failure(Exception("Failed to create cart"))
            }
        }
    }
