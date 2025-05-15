package com.imeanttobe.app901.api.repo

import com.imeanttobe.app901.api.service.MemoService
import com.imeanttobe.app901.data.model.Memo
import javax.inject.Inject

class MemoRepoImpl
    @Inject
    constructor(
        private val memoService: MemoService,
    ) : MemoRepo {
        override suspend fun getAllMemos(): Result<List<Memo>> {
            TODO("Not yet implemented")
        }

        override suspend fun createMemo(memo: Memo): Result<Boolean> {
            TODO("Not yet implemented")
        }

        override suspend fun deleteMemo(memoId: Long): Result<Boolean> {
            TODO("Not yet implemented")
        }
    }
