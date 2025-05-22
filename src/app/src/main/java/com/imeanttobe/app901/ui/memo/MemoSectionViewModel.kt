package com.imeanttobe.app901.ui.memo

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.imeanttobe.app901.ProtoMemoItem
import com.imeanttobe.app901.api.repo.MemoRepo
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MemoSectionViewModel
    @Inject
    constructor(
        private val memoRepo: MemoRepo,
    ) : ViewModel() {
        // Variables
        private val _memos = MutableStateFlow<List<ProtoMemoItem>>(emptyList())
        val memos: StateFlow<List<ProtoMemoItem>> = _memos

        // Functions
        fun addMemo(content: String) {
            viewModelScope.launch { memoRepo.addMemo(content = content) }
        }
    }
