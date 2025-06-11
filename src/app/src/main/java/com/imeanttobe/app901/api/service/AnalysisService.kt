package com.imeanttobe.app901.api.service

import com.imeanttobe.app901.api.body.CompleteAnalysisResponse
import com.imeanttobe.app901.api.body.ConfirmAnalysisResponse
import com.imeanttobe.app901.api.body.CreateAnalysisResponse
import com.imeanttobe.app901.api.body.CreateCartRequest
import com.imeanttobe.app901.api.body.DeleteAnalysisResponse
import com.imeanttobe.app901.api.body.GetAnalysisByIdResponse
import com.imeanttobe.app901.data.enum.GetAllCartsStatus
import com.imeanttobe.app901.data.model.SimplifiedAnalysis
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.PUT
import retrofit2.http.Path
import retrofit2.http.Query

interface AnalysisService {
    @GET("/api/carts")
    suspend fun getAllCarts(
        @Query("user_id") userId: String,
        @Query("status") status: GetAllCartsStatus,
    ): Response<List<SimplifiedAnalysis>>

    @GET("/api/carts/{cartId}")
    suspend fun getCartById(
        @Path("cartId") cartId: Long,
    ): Response<GetAnalysisByIdResponse>

    @POST("/api/carts/{cartId}")
    suspend fun confirmCart(
        @Path("cartId") cartId: Long,
        @Query("user_id") userId: String,
    ): Response<ConfirmAnalysisResponse>

    @PUT("/api/carts/{cartId}/complete")
    suspend fun completeCart(
        @Path("cartId") cartId: Long,
        @Query("user_id") userId: String,
    ): Response<CompleteAnalysisResponse>

    @DELETE("/api/carts/{cartId}")
    suspend fun deleteCart(
        @Path("cartId") cartId: Long,
        @Query("user_id") userId: String,
    ): Response<DeleteAnalysisResponse>

    @POST("/api/carts")
    suspend fun createCart(
        @Body request: CreateCartRequest,
    ): Response<CreateAnalysisResponse>
}
