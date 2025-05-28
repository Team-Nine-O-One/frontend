package com.imeanttobe.app901.api.repo

import com.imeanttobe.app901.data.model.Cart
import com.imeanttobe.app901.data.model.SimplifiedHistory

interface CartRepo {
    suspend fun getAllCarts(userId: String): Result<List<SimplifiedHistory>>

    suspend fun getCartById(
        cartId: Long,
        userId: String,
    ): Result<Cart>

    suspend fun createCart(
        userId: String,
        memoContents: String,
    ): Result<Cart>

    suspend fun confirmCart(
        cartId: Long,
        userId: String,
    ): Result<Boolean>

    suspend fun updateWeights(
        cartId: Long,
        userId: String,
        priceWeight: Double,
        distanceWeight: Double,
    ): Result<Boolean>

    suspend fun completeCart(
        cartId: Long,
        userId: String,
    ): Result<Boolean>
}
