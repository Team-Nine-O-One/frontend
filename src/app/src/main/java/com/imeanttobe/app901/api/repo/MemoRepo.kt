package com.imeanttobe.app901.api.repo

import com.imeanttobe.app901.ProtoMemoItem
import kotlinx.coroutines.flow.Flow

interface MemoRepo {
    val getMemosFlow: Flow<List<ProtoMemoItem>>

    suspend fun saveMemos(memos: List<ProtoMemoItem>)

    suspend fun addMemo(content: String)

    suspend fun removeMemo(memoId: Long)
}

/*
    // Example function to create a new MemoLeaf (for demonstration)
    fun createNewMemoLeaf(content: String): Memo {
        val leaf = MemoLeaf.newBuilder()
            .setId(UUID.randomUUID().toString())
            .setContent(content)
            .setCreatedAtTimestampMs(System.currentTimeMillis())
            .setUpdatedAtTimestampMs(System.currentTimeMillis())
            .build()
        return Memo.newBuilder()
            .setId(leaf.id) // Ensure wrapper ID matches leaf ID
            .setLeaf(leaf)
            .build()
    }

    // Example function to create a new MemoGroup (for demonstration)
    suspend fun createNewMemoGroup(name: String, containedMemoIds: List<String>): Memo {
        val currentMemos = memosFlow.first().filter { it.id in containedMemoIds } // Get actual memo objects
        val group = MemoGroup.newBuilder()
            .setId(UUID.randomUUID().toString())
            .setName(name)
            .setCreatedAtTimestampMs(System.currentTimeMillis())
            .setUpdatedAtTimestampMs(System.currentTimeMillis())
            .addAllMemos(currentMemos) // Add the actual Memo objects
            .build()
        return Memo.newBuilder()
            .setId(group.id) // Ensure wrapper ID matches group ID
            .setGroup(group)
            .build()
    }

 */
