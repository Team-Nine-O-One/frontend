package com.imeanttobe.app901.api.service

import com.imeanttobe.app901.api.response.CompleteCartResponse
import com.imeanttobe.app901.api.response.ConfirmCartResponse
import com.imeanttobe.app901.api.response.CreateCartResponse
import com.imeanttobe.app901.api.response.GetAllCartsResponse
import com.imeanttobe.app901.api.response.GetCartByIdResponse
import com.imeanttobe.app901.api.response.UpdateWeightResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.PUT
import retrofit2.http.Path

interface CartService {
    @GET("/api/carts")
    suspend fun getAllCarts(userId: String): Response<GetAllCartsResponse>

    @GET("/api/carts/{cartId}")
    suspend fun getCartById(
        @Path("cartId") cartId: Long,
        userId: String,
    ): Response<GetCartByIdResponse>

    @POST("/api/carts")
    suspend fun createCart(
        userId: String,
        memoId: Long,
    ): Response<CreateCartResponse>

    @POST("/api/carts/{cartId}")
    suspend fun confirmCart(
        @Path("cartId") cartId: Long,
        userId: String,
    ): Response<ConfirmCartResponse>

    @PUT("/api/carts/{cartId}/weights")
    suspend fun updateWeights(
        @Path("cartId") cartId: Long,
        userId: String,
        priceWeight: Double,
        distanceWeight: Double,
    ): Response<UpdateWeightResponse>

    @PUT("/api/carts/{cartId}/complete")
    suspend fun completeCart(
        @Path("cartId") cartId: Long,
        userId: String,
    ): Response<CompleteCartResponse>
}
