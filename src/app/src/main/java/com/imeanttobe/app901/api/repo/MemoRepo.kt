package com.imeanttobe.app901.api.repo

import com.imeanttobe.app901.data.model.Memo

interface MemoRepo {
    suspend fun getAllMemos(userId: String): Result<List<Memo>>

    suspend fun createMemo(
        userId: String,
        memo: Memo,
    ): Result<Boolean>

    suspend fun deleteMemo(
        userId: String,
        memoId: Long,
    ): Result<Boolean>
}
