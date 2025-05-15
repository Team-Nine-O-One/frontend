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
            TODO("Not yet implemented")
        }

        override suspend fun getCartById(
            cartId: Long,
            userId: String,
        ): Result<Cart> {
            TODO("Not yet implemented")
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
            TODO("Not yet implemented")
        }

        override suspend fun updateWeights(
            cartId: Long,
            userId: String,
            priceWeight: Double,
            distanceWeight: Double,
        ): Result<Boolean> {
            TODO("Not yet implemented")
        }

        override suspend fun completeCart(
            cartId: Long,
            userId: String,
        ): Result<Boolean> {
            TODO("Not yet implemented")
        }
    }
