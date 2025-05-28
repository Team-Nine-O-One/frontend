package com.imeanttobe.app901.api.repo

import com.imeanttobe.app901.data.model.Cart
import com.imeanttobe.app901.data.model.SimplifiedHistory
import com.imeanttobe.app901.data.model.SimplifiedMart
import javax.inject.Inject

class FakeCartRepoImpl
    @Inject
    constructor() : CartRepo {
        private val sampleHistory =
            SimplifiedHistory(
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
            )

        override suspend fun getAllCarts(userId: String): Result<List<SimplifiedHistory>> {
            val mockedResponse =
                listOf(
                    sampleHistory.copy(),
                    sampleHistory.copy(),
                    sampleHistory.copy(),
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
            memoContents: String,
        ): Result<Cart> {
            val mockedResponse = Cart.getDefaultInstance()
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

        override suspend fun deleteCart(
            cartId: Long,
            userId: String,
        ): Result<Boolean> {
            val mockedResponse = true
            return Result.success(mockedResponse)
        }
    }
