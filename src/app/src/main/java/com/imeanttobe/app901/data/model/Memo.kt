package com.imeanttobe.app901.data.model

import com.imeanttobe.app901.data.type.MemoItem
import java.time.LocalDateTime

data class Memo(
    val memoId: Long, // PK
    val contents: MutableList<MemoItem> = mutableListOf(),
    val createdAt: LocalDateTime = LocalDateTime.now(), // DATETIME
) {
    companion object {
        fun getDefaultInstance(): Memo = Memo(
            memoId = -1,
            contents = mutableListOf(),
            createdAt = LocalDateTime.now()
        )
    }
}
