package com.imeanttobe.app901.api.service

import com.imeanttobe.app901.api.response.CreateMemoResponse
import com.imeanttobe.app901.api.response.GetAllMemosResponse
import retrofit2.Response
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.POST

interface MemoService {
    @GET("/api/memos")
    suspend fun getAllMemos(userId: String): Response<GetAllMemosResponse>

    @POST("/api/memos")
    suspend fun createMemo(
        userId: String,
        contents: String,
    ): Response<CreateMemoResponse>

    @DELETE
    suspend fun deleteMemo(
        memoId: Long,
        userId: String,
    ): Response<Boolean>
}
