package com.imeanttobe.app901.api.service

import com.imeanttobe.app901.api.response.GetAllMemoResponse
import retrofit2.Response
import retrofit2.http.GET

interface MemoService {
    @GET("/api/memos")
    suspend fun getAllMemos(): Response<GetAllMemoResponse>
}
