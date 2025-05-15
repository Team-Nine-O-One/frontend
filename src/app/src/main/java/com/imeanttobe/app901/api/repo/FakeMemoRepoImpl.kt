package com.imeanttobe.app901.api.repo

import com.imeanttobe.app901.data.model.Memo
import com.imeanttobe.app901.data.type.MemoItemLeaf
import java.time.LocalDateTime
import javax.inject.Inject

class FakeMemoRepoImpl
    @Inject
    constructor() : MemoRepo {
        override suspend fun getAllMemos(userId: String): Result<List<Memo>> {
            val mockedResponse =
                listOf(
                    Memo(
                        memoId = 1,
                        contents =
                            mutableListOf(
                                MemoItemLeaf("파 한단", false),
                                MemoItemLeaf("고기 500g", false),
                            ),
                        createdAt = LocalDateTime.now(),
                    ),
                    Memo(
                        memoId = 2,
                        contents =
                            mutableListOf(
                                MemoItemLeaf("우유 1개", false),
                            ),
                        createdAt = LocalDateTime.now(),
                    ),
                )
            return Result.success(mockedResponse)
        }

        override suspend fun createMemo(
            userId: String,
            memo: Memo,
        ): Result<Boolean> {
            val mockedResponse = true
            return Result.success(mockedResponse)
        }

        override suspend fun deleteMemo(
            userId: String,
            memoId: Long,
        ): Result<Boolean> {
            val mockedResponse = true
            return Result.success(mockedResponse)
        }
    }
