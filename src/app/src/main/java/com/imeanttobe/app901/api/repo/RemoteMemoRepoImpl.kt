package com.imeanttobe.app901.api.repo

import com.imeanttobe.app901.api.body.CreateRemoteMemoRequest
import com.imeanttobe.app901.api.service.RemoteMemoService
import javax.inject.Inject

class RemoteMemoRepoImpl
    @Inject
    constructor(
        private val service: RemoteMemoService,
    ) : RemoteMemoRepo {
        override suspend fun createMemo(
            userId: String,
            rawText: String,
            userLat: Double,
            userLng: Double,
        ): Result<Long> {
            val response = service.createMemo(CreateRemoteMemoRequest(userId, rawText, userLat, userLng))
            return if (response.isSuccessful) {
                Result.success(response.body()!!.memo_id)
            } else {
                Result.failure(Exception(response.message()))
            }
        }
    }
