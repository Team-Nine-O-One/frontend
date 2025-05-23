package com.imeanttobe.app901.ui.memo

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.imeanttobe.app901.ProtoMemoItem
import com.imeanttobe.app901.api.repo.MemoRepo
import com.imeanttobe.app901.data.type.ConcurrencyState
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
        private val _deleteAllMemosDialogState = mutableStateOf(false)

        val deleteAllMemosDialogState: State<Boolean> = _deleteAllMemosDialogState

        val memos: StateFlow<List<ProtoMemoItem>> =
            memoRepo.getMemosFlow.stateIn(
                scope = viewModelScope,
                started = SharingStarted.WhileSubscribed(5000),
                initialValue = emptyList(),
            )

        // Functions
        fun setDeleteAllMemosDialogState(value: Boolean) {
            _deleteAllMemosDialogState.value = value
        }

        fun removeMemo(item: ProtoMemoItem) {
            viewModelScope.launch {
                memoRepo.removeMemo(memoId = item.id)
                memoStateHolder.removeMemo(item)
            }
        }

        fun isChecked(id: Long): Boolean {
            var concurrencyState: ConcurrencyState = ConcurrencyState.Default

            viewModelScope.launch {
                val result = memoStateHolder.isChecked(id)
                concurrencyState = ConcurrencyState.Success(result)
            }

            return when (concurrencyState) {
                is ConcurrencyState.Success -> concurrencyState.result as Boolean
                else -> false
            }
        }

        fun setChecked(
            item: ProtoMemoItem,
            value: Boolean,
        ) {
            viewModelScope.launch {
                memoStateHolder.setChecked(item, value)
            }
        }
    }
