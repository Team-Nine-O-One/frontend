package com.imeanttobe.app901.data.type

import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateMapOf
import androidx.compose.runtime.snapshots.SnapshotStateList
import com.imeanttobe.app901.ProtoMemoItem
import javax.inject.Inject

class MemoStateHolder
    @Inject
    constructor(
        private val idGenerator: IdGenerator,
    ) {
        val checkStates = mutableStateMapOf<Long, Boolean>()
        val memos: SnapshotStateList<ProtoMemoItem> = mutableStateListOf()

        fun isChecked(id: Long): Boolean = checkStates[id] == true

        fun setChecked(
            id: Long,
            value: Boolean,
        ) {
            checkStates[id] = value
        }

        fun addMemo(item: ProtoMemoItem) {
            memos.add(item)
            checkStates[item.id] = false
            if (item.isLeaf == false) {
                item.itemsList.forEach {
                    checkStates[it.id] = false
                }
            }
        }

        fun removeMemo(item: ProtoMemoItem) {
            memos.remove(item)
            checkStates.remove(item.id)
            if (item.isLeaf == false) {
                item.itemsList.forEach {
                    checkStates.remove(it.id)
                }
            }
        }
    }
