package com.imeanttobe.app901.api.repo

import com.imeanttobe.app901.api.service.CartService
import com.imeanttobe.app901.data.model.Cart
import com.imeanttobe.app901.data.model.SimplifiedCart
import javax.inject.Inject

class CartRepoImpl
    @Inject
    constructor(
        private val cartService: CartService,
    ) : CartRepo {
        override suspend fun getAllCarts(userId: String): Result<List<SimplifiedCart>> {
            val response = cartService.getAllCarts(userId = userId)
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

        override suspend fun getCartById(
            cartId: Long,
            userId: String,
        ): Result<Cart> {
            val response = cartService.getCartById(cartId = cartId, userId = userId)
            return if (response.isSuccessful) {
                val body = response.body()
                if (body != null) {
                    Result.success(
                        Cart(
                            cartId = body.cartId,
                            memoId = body.memoId,
                            status = body.status,
                            createdAt = body.createdAt,
                            recommendedResults = body.recommendedResult,
                        ),
                    )
                } else {
                    Result.failure(Exception("Response body is null"))
                }
            } else {
                Result.failure(Exception("Failed to fetch cart"))
            }
        }

        override suspend fun createCart(
            userId: String,
            memoId: Long,
        ): Result<Boolean> {
            TODO("Not yet implemented")
        }

        override suspend fun confirmCart(
            cartId: Long,
            userId: String,
        ): Result<Boolean> {
            val response = cartService.confirmCart(cartId = cartId, userId = userId)
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
                cartService.updateWeights(
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

        override suspend fun completeCart(
            cartId: Long,
            userId: String,
        ): Result<Boolean> {
            val response = cartService.completeCart(cartId = cartId, userId = userId)
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
    }
