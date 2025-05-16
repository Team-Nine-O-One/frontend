package com.imeanttobe.app901.api.repo

import com.imeanttobe.app901.data.model.Cart
import com.imeanttobe.app901.data.model.SimplifiedCart
import javax.inject.Inject

class FakeCartRepoImpl
    @Inject
    constructor() : CartRepo {
        override suspend fun getAllCarts(userId: String): Result<List<SimplifiedCart>> {
            val mockedResponse =
                listOf(
                    SimplifiedCart.getDefaultInstance(),
                    SimplifiedCart.getDefaultInstance(),
                    SimplifiedCart.getDefaultInstance(),
                )
            return Result.success(mockedResponse)
        }

        override suspend fun getCartById(
            cartId: Long,
            userId: String,
        ): Result<Cart> {
            val mockedResponse = Cart.getDefaultInstance()
            return Result.success(mockedResponse)
        }

        override suspend fun createCart(
            userId: String,
            memoId: Long,
        ): Result<Boolean> {
            val mockedResponse = true
            return Result.success(mockedResponse)
        }

        override suspend fun confirmCart(
            cartId: Long,
            userId: String,
        ): Result<Boolean> {
            val mockedResponse = true
            return Result.success(mockedResponse)
        }

        override suspend fun updateWeights(
            cartId: Long,
            userId: String,
            priceWeight: Double,
            distanceWeight: Double,
        ): Result<Boolean> {
            val mockedResponse = true
            return Result.success(mockedResponse)
        }

        override suspend fun completeCart(
            cartId: Long,
            userId: String,
        ): Result<Boolean> {
            val mockedResponse = true
            return Result.success(mockedResponse)
        }
    }
