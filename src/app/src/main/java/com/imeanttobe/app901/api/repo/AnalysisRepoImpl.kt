package com.imeanttobe.app901.api.repo

import com.imeanttobe.app901.api.service.AnalysisService
import com.imeanttobe.app901.data.model.Analysis
import com.imeanttobe.app901.data.model.SimplifiedAnalysis
import javax.inject.Inject

class AnalysisRepoImpl
    @Inject
    constructor(
        private val analysisService: AnalysisService,
    ) : AnalysisRepo {
        override suspend fun getAllAnalyses(userId: String): Result<List<SimplifiedAnalysis>> {
            val response = analysisService.getAllCarts(userId = userId)

            return if (response.isSuccessful) {
                val body = response.body()
                if (body != null) {
                    Result.success(body.carts)
                } else {
                    Result.failure(Exception("Response body is null"))
                }
            } else {
                Result.failure(Exception("Failed to fetch carts"))
            }
        }

        override suspend fun getAnalysisById(
            analysisId: Long,
            userId: String,
        ): Result<Analysis> {
            val response = analysisService.getCartById(cartId = analysisId, userId = userId)
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

        override suspend fun createAnalysis(
            userId: String,
            memoContents: String,
        ): Result<Analysis> {
            val response = analysisService.createCart(userId = userId, memoContents = memoContents)
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
                Result.failure(Exception("Failed to create cart"))
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

        override suspend fun updateWeights(
            analysisId: Long,
            userId: String,
            priceWeight: Double,
            distanceWeight: Double,
        ): Result<Boolean> {
            val response =
                analysisService.updateWeights(
                    cartId = analysisId,
                    userId = userId,
                    priceWeight = priceWeight,
                    distanceWeight = distanceWeight,
                )
            return if (response.isSuccessful) {
                val body = response.body()
                if (body != null) {
                    Result.success(true)
                } else {
                    Result.failure(Exception("Response body is null"))
                }
            } else {
                Result.failure(Exception("Failed to update weights"))
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
    }
