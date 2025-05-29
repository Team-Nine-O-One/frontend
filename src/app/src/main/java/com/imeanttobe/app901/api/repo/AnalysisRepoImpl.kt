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
            cartId: Long,
            userId: String,
        ): Result<Analysis> {
            val response = analysisService.getCartById(cartId = cartId, userId = userId)
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
            cartId: Long,
            userId: String,
        ): Result<Boolean> {
            val response = analysisService.confirmCart(cartId = cartId, userId = userId)
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
            cartId: Long,
            userId: String,
            priceWeight: Double,
            distanceWeight: Double,
        ): Result<Boolean> {
            val response =
                analysisService.updateWeights(
                    cartId = cartId,
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
            cartId: Long,
            userId: String,
        ): Result<Boolean> {
            val response = analysisService.completeCart(cartId = cartId, userId = userId)
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
            cartId: Long,
            userId: String,
        ): Result<Boolean> {
            val response = analysisService.deleteCart(cartId = cartId, userId = userId)

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
