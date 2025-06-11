package com.imeanttobe.app901.ui.memo

import android.util.Log
import androidx.compose.runtime.State
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.mutableStateMapOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.state.ToggleableState
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.imeanttobe.app901.ProtoMemoItem
import com.imeanttobe.app901.api.repo.AnalysisRepo
import com.imeanttobe.app901.api.repo.MemoRepo
import com.imeanttobe.app901.api.repo.UserRepo
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
        private val userRepo: UserRepo,
        private val analysisRepo: AnalysisRepo,
    ) : ViewModel() {
        // Variables
        val checkedState = mutableStateMapOf<Long, ToggleableState>()
        val memos: StateFlow<List<ProtoMemoItem>> =
            memoRepo.getMemosFlow.stateIn(
                scope = viewModelScope,
                started = SharingStarted.WhileSubscribed(5000),
                initialValue = emptyList(),
            )
        val isAllUncheckedState: State<Boolean> =
            derivedStateOf {
                val currentMemos = memos.value
                val currentCheckedState = checkedState

                if (currentMemos.isEmpty() || currentCheckedState.isEmpty()) {
                    true
                } else {
                    // Get all relevant item IDs (both leaf items and sub-items of non-leaf items)
                    val allRelevantItemIds =
                        currentMemos.flatMap { item ->
                            listOf(item.id) +
                                if (!item.isLeaf) {
                                    // If not a leaf, take IDs of its sub-items
                                    item.itemsList.map { subItem -> subItem.id }
                                } else {
                                    emptyList()
                                }
                        }

                    // Check if all these items are in the "Off" state
                    allRelevantItemIds.all { itemId ->
                        currentCheckedState[itemId] == ToggleableState.Off || currentCheckedState[itemId] == null
                    }
                }
            }

        private val _deleteAllMemosDialogState = mutableStateOf(false)
        private val _overallToggleState = mutableStateOf(ToggleableState.Off)

        val deleteAllMemosDialogState: State<Boolean> = _deleteAllMemosDialogState
        val overallToggleState: State<ToggleableState> = _overallToggleState

        // Functions
        fun setDeleteAllMemosDialogState(value: Boolean) {
            _deleteAllMemosDialogState.value = value
        }

        fun updateOverallToggleState() {
            val leafIds =
                memos.value.flatMap { item ->
                    if (item.isLeaf) {
                        listOf(item.id)
                    } else {
                        item.itemsList.map { subItem -> subItem.id }
                    }
                }
            val checkedCount = leafIds.count { id -> checkedState[id] == ToggleableState.On }

            when (checkedCount) {
                leafIds.size -> _overallToggleState.value = ToggleableState.On
                0 -> _overallToggleState.value = ToggleableState.Off
                else -> _overallToggleState.value = ToggleableState.Indeterminate
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
            updateOverallToggleState()
        }

        fun onToggleOverall() {
            val newValue = _overallToggleState.value == ToggleableState.Off
            memos.value.forEach { item ->
                setChecked(item, newValue)
            }
        }

        fun onToggleGroup(
            item: ProtoMemoItem,
            newValue: Boolean,
        ) {
            setChecked(item, newValue)
            updateOverallToggleState()
        }

        fun deleteMemo(item: ProtoMemoItem) {
            viewModelScope.launch { memoRepo.removeMemo(item) }
            updateOverallToggleState()
        }

        fun deleteMemoLeafInGroup(
            parent: ProtoMemoItem,
            itemToRemove: ProtoMemoItem,
        ) {
            viewModelScope.launch {
                memoRepo.removeMemoLeafInGroup(parent, itemToRemove)
                updateOverallToggleState()
            }
        }

        fun deleteCheckedMemos() {
            val memosToDelete =
                if (isAllUncheckedState.value) {
                    memos.value
                } else {
                    memos.value.flatMap { item ->
                        when {
                            item.isLeaf && checkedState[item.id] == ToggleableState.On -> listOf(item)
                            !item.isLeaf && getGroupToggleState(item) == ToggleableState.On -> listOf(item)
                            !item.isLeaf ->
                                item.itemsList.filter { subItem ->
                                    checkedState[subItem.id] == ToggleableState.On
                                }
                            else -> emptyList()
                        }
                    }
                }
            Log.d("deleteCheckedMemos", "memosToDelete: $memosToDelete")

            // Create a lookup for groups for faster access
            val groupLookup = memos.value.filter { !it.isLeaf }.associateBy { it.id }

            viewModelScope.launch {
                val groups = memos.value.filter { item -> !item.isLeaf }

                memosToDelete.forEach { item ->
                    if (item.isLeaf) {
                        if (groups.any { group -> group.itemsList.contains(item) }) {
                            val parent = groups.first { group -> group.itemsList.contains(item) }
                            // Use the lookup for faster parent retrieval if possible
                            // This part assumes that `item` itself might not have a direct parent ID
                            // If `item` has a `parentId` field, it would be more efficient.
                            // For now, sticking to the existing logic but noting the potential optimization.
                            groupLookup[parent.id]?.let { memoRepo.removeMemoLeafInGroup(it, item) }
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

                updateOverallToggleState()
            }
        }

        fun deleteAllMemos() {
            viewModelScope.launch { memoRepo.removeAllMemos() }
        }

        fun editMemo(
            item: ProtoMemoItem,
            newContent: String,
        ) {
            viewModelScope.launch { memoRepo.editMemo(item, newContent) }
        }

        fun editMemoLeafInGroup(
            parent: ProtoMemoItem,
            item: ProtoMemoItem,
            newContent: String,
        ) {
            viewModelScope.launch { memoRepo.editMemoLeafInGroup(parent, item, newContent) }
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

        fun createAnalysis(
            navigate: (Long) -> Unit,
            showToast: (String) -> Unit,
        ) {
            viewModelScope.launch {
                val result =
                    analysisRepo.createAnalysis(
                        userId = userRepo.getUserId(),
                        memoContents = memoRepo.exportToString(),
                    )
                if (result.isSuccess) {
                    val analysisId = result.getOrNull()
                    if (analysisId != null) {
                        deleteAllMemos()
                        navigate(analysisId)
                    } else {
                        showToast("분석 데이터를 불러오는 데 실패했어요.")
                    }
                } else {
                    showToast("분석 요청에 실패했어요.")
                }
            }
        }
    }
