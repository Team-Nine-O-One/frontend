package com.imeanttobe.app901.ui.memo

import android.util.Log
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateMapOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.state.ToggleableState
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.imeanttobe.app901.ProtoMemoItem
import com.imeanttobe.app901.api.repo.MemoRepo
import com.imeanttobe.app901.data.type.MemoStateHolder
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MemoSectionViewModel
    @Inject
    constructor(
        private val memoRepo: MemoRepo,
        private val memoStateHolder: MemoStateHolder,
    ) : ViewModel() {
        // Variables
        val checkedState = mutableStateMapOf<Long, ToggleableState>()
        val memos: StateFlow<List<ProtoMemoItem>> =
            memoRepo.getMemosFlow.stateIn(
                scope = viewModelScope,
                started = SharingStarted.WhileSubscribed(5000),
                initialValue = emptyList(),
            )

        private val _deleteAllMemosDialogState = mutableStateOf(false)

        val deleteAllMemosDialogState: State<Boolean> = _deleteAllMemosDialogState

        // Functions
        fun isAllUnchecked(): Boolean =
            memos.value.all { item ->
                if (item.isLeaf) {
                    checkedState[item.id] == ToggleableState.Off
                } else {
                    item.itemsList.all { subItem ->
                        checkedState[subItem.id] == ToggleableState.Off
                    }
                }
            }

        fun setDeleteAllMemosDialogState(value: Boolean) {
            _deleteAllMemosDialogState.value = value
        }

        fun getOverallToggleState(): ToggleableState {
            val leafIds =
                memos.value.flatMap { item ->
                    if (item.isLeaf) {
                        listOf(item.id)
                    } else {
                        item.itemsList.map { subItem -> subItem.id }
                    }
                }
            val checkedCount = leafIds.count { id -> checkedState[id] == ToggleableState.On }

            return when (checkedCount) {
                leafIds.size -> ToggleableState.On
                0 -> ToggleableState.Off
                else -> ToggleableState.Indeterminate
            }
        }

        fun putMemosInMap() {
            memos.value.forEach { item ->
                checkedState[item.id] = ToggleableState.Off
                if (!item.isLeaf) {
                    item.itemsList.forEach { subItem ->
                        checkedState[subItem.id] = ToggleableState.Off
                    }
                }
            }
        }

        fun isChecked(item: ProtoMemoItem): ToggleableState {
            if (item.isLeaf) {
                return checkedState[item.id] ?: ToggleableState.Off
            } else {
                return getGroupToggleState(item)
            }
        }

        fun setChecked(
            item: ProtoMemoItem,
            value: Boolean,
        ) {
            checkedState[item.id] = if (value) ToggleableState.On else ToggleableState.Off
            if (!item.isLeaf) {
                item.itemsList.forEach { subItem ->
                    checkedState[subItem.id] = if (value) ToggleableState.On else ToggleableState.Off
                }
            }
        }

        fun onToggleOverall() {
            val newValue = getOverallToggleState() == ToggleableState.Off
            memos.value.forEach { item ->
                setChecked(item, newValue)
            }
        }

        fun onToggleGroup(
            item: ProtoMemoItem,
            newValue: Boolean,
        ) {
            setChecked(item, newValue)
        }

        fun deleteMemo(item: ProtoMemoItem) {
            viewModelScope.launch { memoRepo.removeMemo(item) }
        }

        fun deleteMemoLeafInGroup(
            parent: ProtoMemoItem,
            itemToRemove: ProtoMemoItem,
        ) {
            viewModelScope.launch { memoRepo.removeMemoLeafInGroup(parent, itemToRemove) }
        }

        fun deleteCheckedMemos() {
            val memosToDelete = mutableListOf<ProtoMemoItem>()
            memos.value.forEach { item ->
                if (item.isLeaf) {
                    if (checkedState[item.id] == ToggleableState.On) {
                        memosToDelete.add(item)
                    }
                } else {
                    if (getGroupToggleState(item) == ToggleableState.On) {
                        memosToDelete.add(item)
                    } else {
                        item.itemsList.forEach { subItem ->
                            if (checkedState[subItem.id] == ToggleableState.On) {
                                memosToDelete.add(subItem)
                            }
                        }
                    }
                }
            }
            Log.d("deleteCheckedMemos", "memosToDelete: $memosToDelete")

            viewModelScope.launch {
                val groups = memos.value.filter { item -> !item.isLeaf }

                memosToDelete.forEach { item ->
                    if (item.isLeaf) {
                        if (groups.any { group -> group.itemsList.contains(item) }) {
                            val parent = groups.first { group -> group.itemsList.contains(item) }
                            memoRepo.removeMemoLeafInGroup(parent, item)
                        } else {
                            memoRepo.removeMemo(item)
                        }
                    } else {
                        item.itemsList.forEach { subItem ->
                            memoRepo.removeMemoLeafInGroup(item, subItem)
                        }
                        memoRepo.removeMemo(item)
                    }
                }
            }
        }

        private fun getGroupToggleState(item: ProtoMemoItem): ToggleableState {
            val leafIds = item.itemsList.map { subItem -> subItem.id }
            val checkedCount = leafIds.count { id -> checkedState[id] == ToggleableState.On }

            return when (checkedCount) {
                leafIds.size -> ToggleableState.On
                0 -> ToggleableState.Off
                else -> ToggleableState.Indeterminate
            }
        }
    }
