package com.imeanttobe.app901.api.service

import com.imeanttobe.app901.api.body.CreateRemoteMemoRequest
import com.imeanttobe.app901.api.body.CreateRemoteMemoResponse
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.POST

interface RemoteMemoService {
    @POST("/api/memos")
    suspend fun createMemo(
        @Body request: CreateRemoteMemoRequest,
    ): Response<CreateRemoteMemoResponse>
}
