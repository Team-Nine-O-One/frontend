package com.imeanttobe.app901.ui.memo

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
        val memoStateHolder: MemoStateHolder,
    ) : ViewModel() {
        // Variables
        val memos: StateFlow<List<ProtoMemoItem>> =
            memoRepo.getMemosFlow.stateIn(
                scope = viewModelScope,
                started = SharingStarted.WhileSubscribed(5000),
                initialValue = emptyList(),
            )

        // Functions
        fun addMemo(content: String) {
            viewModelScope.launch {
                val newMemo = memoRepo.addMemo(content = content)
                memoStateHolder.addMemo(newMemo)
            }
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
            id: Long,
            value: Boolean,
        ) {
            viewModelScope.launch {
                memoStateHolder.setChecked(id, value)
            }
        }
    }
