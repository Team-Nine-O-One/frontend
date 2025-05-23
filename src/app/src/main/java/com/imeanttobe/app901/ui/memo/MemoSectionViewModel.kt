package com.imeanttobe.app901.ui.memo

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.imeanttobe.app901.ProtoMemoItem
import com.imeanttobe.app901.api.repo.MemoRepo
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
    ) : ViewModel() {
        // Variables
        val memos: StateFlow<List<ProtoMemoItem>> =
            memoRepo.getMemosFlow.stateIn(
                scope = viewModelScope,
                started = SharingStarted.WhileSubscribed(5000),
                initialValue = emptyList(),
            )
        val

        // Functions
        fun addMemo(content: String) {
            viewModelScope.launch { memoRepo.addMemo(content = content) }
        }
    }
