package com.imeanttobe.app901.api.repo

import com.imeanttobe.app901.api.service.MemoService
import com.imeanttobe.app901.data.model.Memo
import javax.inject.Inject

class MemoRepoImpl
    @Inject
    constructor(
        private val memoService: MemoService,
    ) : MemoRepo {
        override suspend fun getAllMemos(userId: String): Result<List<Memo>> {
            val response = memoService.getAllMemos(userId = userId)
            return if (response.isSuccessful) {
                val body = response.body()
                if (body != null) {
                    Result.success(body.items)
                } else {
                    Result.failure(Exception("Response body is null"))
                }
            } else {
                Result.failure(Exception("Failed to fetch memos"))
            }
        }

        override suspend fun createMemo(
            userId: String,
            memo: Memo,
        ): Result<Boolean> {
            var contents = ""
            memo.contents.forEachIndexed { index, item ->
                contents +=
                    if (index != 0) {
                        item.getContent() + ", "
                    } else {
                        item.getContent()
                    }
            }

            val response = memoService.createMemo(userId = userId, contents = contents)
            return if (response.isSuccessful) {
                val body = response.body()
                if (body != null) {
                    Result.success(true)
                } else {
                    Result.failure(Exception("Response body is null"))
                }
            } else {
                Result.failure(Exception("Failed to create memo"))
            }
        }

        override suspend fun deleteMemo(
            userId: String,
            memoId: Long,
        ): Result<Boolean> {
            val response = memoService.deleteMemo(memoId = memoId, userId = userId)
            return if (response.isSuccessful) {
                val body = response.body()
                if (body != null) {
                    Result.success(true)
                } else {
                    Result.failure(Exception("Response body is null"))
                }
            } else {
                Result.failure(Exception("Failed to delete memo"))
            }
        }
    }
