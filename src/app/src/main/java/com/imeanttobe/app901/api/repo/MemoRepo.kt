package com.imeanttobe.app901.api.repo

import com.imeanttobe.app901.ProtoMemoItem
import kotlinx.coroutines.flow.Flow

interface MemoRepo {
    val getMemosFlow: Flow<List<ProtoMemoItem>>

    suspend fun addMemoLeaf(content: String): ProtoMemoItem

    suspend fun addMemoGroup(
        title: String,
        contents: List<String>,
    ): ProtoMemoItem

    suspend fun editMemo(
        itemToEdit: ProtoMemoItem,
        newContent: String,
    ): ProtoMemoItem?

    suspend fun editMemoLeafInGroup(
        parent: ProtoMemoItem,
        itemToEdit: ProtoMemoItem,
        newContent: String,
    ): ProtoMemoItem?

    suspend fun removeMemo(memo: ProtoMemoItem)

    suspend fun removeMemoLeafInGroup(
        parent: ProtoMemoItem,
        itemToRemove: ProtoMemoItem,
    )

    suspend fun removeAllMemos()

    suspend fun exportToString(): String
}
