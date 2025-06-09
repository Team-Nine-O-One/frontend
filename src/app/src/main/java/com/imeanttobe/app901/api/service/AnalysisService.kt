package com.imeanttobe.app901.api.service

import com.imeanttobe.app901.api.response.CompleteAnalysisResponse
import com.imeanttobe.app901.api.response.ConfirmAnalysisResponse
import com.imeanttobe.app901.api.response.CreateAnalysisResponse
import com.imeanttobe.app901.api.response.DeleteAnalysisResponse
import com.imeanttobe.app901.api.response.GetAnalysisByIdResponse
import com.imeanttobe.app901.data.enum.GetAllCartsStatus
import com.imeanttobe.app901.data.model.SimplifiedAnalysis
import retrofit2.Response
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
        userId: String,
        memo: String,
    ): Response<CreateAnalysisResponse>
}
