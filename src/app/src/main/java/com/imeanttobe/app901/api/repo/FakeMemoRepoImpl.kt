package com.imeanttobe.app901.api.repo

import com.imeanttobe.app901.api.response.GetAllMemoResponse
import com.imeanttobe.app901.api.service.MemoService
import com.imeanttobe.app901.data.model.Memo
import com.imeanttobe.app901.data.type.MemoItemLeaf
import java.time.LocalDateTime

class FakeMemoRepoImpl(
    private val memoService: MemoService,
) : MemoRepo {
    override suspend fun getAllMemos(): Result<List<Memo>> {
        val response =
            GetAllMemoResponse(
                listOf(
                    Memo(
                        memoId = 1,
                        contents = mutableListOf(MemoItemLeaf("우유 1개", false), MemoItemLeaf("파 한단", false), MemoItemLeaf("고기 500g", false)),
                        createdAt = LocalDateTime.now(),
                    ),
                ),
            )
        return Result.success(response.items)
    }
}
