package com.imeanttobe.app901.data.type

import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateMapOf
import androidx.compose.runtime.snapshots.SnapshotStateList
import com.imeanttobe.app901.ProtoMemoItem
import kotlinx.coroutines.sync.Mutex
import kotlinx.coroutines.sync.withLock
import javax.inject.Inject

class MemoStateHolder
    @Inject
    constructor(
        private val idGenerator: IdGenerator,
    ) {
        private val mutex = Mutex()
        val checkStates = mutableStateMapOf<Long, Boolean>()
        val memos: SnapshotStateList<ProtoMemoItem> = mutableStateListOf()

        suspend fun isChecked(id: Long): Boolean {
            mutex.withLock {
                return checkStates[id] == true
            }
        }

        suspend fun setChecked(
            id: Long,
            value: Boolean,
        ) {
            mutex.withLock {
                checkStates[id] = value
            }
        }

        suspend fun addMemo(item: ProtoMemoItem) {
            mutex.withLock {
                memos.add(item)
                checkStates[item.id] = false
                if (item.isLeaf == false) {
                    item.itemsList.forEach {
                        checkStates[it.id] = false
                    }
                }
            }
        }

        suspend fun removeMemo(item: ProtoMemoItem) {
            mutex.withLock {
                memos.remove(item)
                checkStates.remove(item.id)
                if (item.isLeaf == false) {
                    item.itemsList.forEach {
                        checkStates.remove(it.id)
                    }
                }
            }
        }
    }
