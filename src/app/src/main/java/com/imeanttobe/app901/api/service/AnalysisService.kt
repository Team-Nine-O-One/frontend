package com.imeanttobe.app901.api.service

import com.imeanttobe.app901.api.response.CompleteAnalysisResponse
import com.imeanttobe.app901.api.response.ConfirmAnalysisResponse
import com.imeanttobe.app901.api.response.CreateAnalysisResponse
import com.imeanttobe.app901.api.response.DeleteAnalysisResponse
import com.imeanttobe.app901.api.response.GetAllAnalysesResponse
import com.imeanttobe.app901.api.response.GetAnalysisByIdResponse
import com.imeanttobe.app901.api.response.UpdateWeightResponse
import retrofit2.Response
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.PUT
import retrofit2.http.Path

interface AnalysisService {
    @GET("/api/carts")
    suspend fun getAllCarts(userId: String): Response<GetAllAnalysesResponse>

    @GET("/api/carts/{cartId}")
    suspend fun getCartById(
        @Path("cartId") cartId: Long,
        userId: String,
    ): Response<GetAnalysisByIdResponse>

    @POST("/api/carts")
    suspend fun createCart(
        userId: String,
        memoContents: String,
    ): Response<CreateAnalysisResponse>

    @POST("/api/carts/{cartId}")
    suspend fun confirmCart(
        @Path("cartId") cartId: Long,
        userId: String,
    ): Response<ConfirmAnalysisResponse>

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
    ): Response<CompleteAnalysisResponse>

    @DELETE("/api/carts/{cartId}")
    suspend fun deleteCart(
        @Path("cartId") cartId: Long,
        userId: String,
    ): Response<DeleteAnalysisResponse>
}
