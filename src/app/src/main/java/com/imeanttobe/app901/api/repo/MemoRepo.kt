package com.imeanttobe.app901.api.repo

import com.imeanttobe.app901.data.model.Memo

interface MemoRepo {
    suspend fun getAllMemos(): Result<List<Memo>>

    suspend fun createMemo(memo: Memo): Result<Boolean>

    suspend fun deleteMemo(memoId: Long): Result<Boolean>
}
