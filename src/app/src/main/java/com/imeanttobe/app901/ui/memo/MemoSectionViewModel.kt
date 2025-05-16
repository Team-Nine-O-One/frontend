package com.imeanttobe.app901.ui.memo

import androidx.compose.runtime.mutableStateListOf
import androidx.lifecycle.ViewModel
import com.imeanttobe.app901.api.repo.MemoRepo
import com.imeanttobe.app901.data.type.MemoItem
import com.imeanttobe.app901.data.type.MemoItemGroup
import com.imeanttobe.app901.data.type.MemoItemLeaf
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class MemoSectionViewModel
    @Inject
    constructor(
        private val memoRepo: MemoRepo,
    ) : ViewModel() {
        // Variables
        private val _memos =
            mutableStateListOf<MemoItem>(
                MemoItemLeaf("Item 1", false),
                MemoItemLeaf("Item 2", true),
                MemoItemGroup(
                    content = "Group 1",
                    checked = false,
                    items =
                        mutableListOf(
                            MemoItemLeaf("Item 3", false),
                            MemoItemLeaf("Item 4", true),
                        ),
                ),
                MemoItemLeaf("Item 5", false),
            )
        val memos: List<MemoItem> = _memos

        // Functions
        fun addMemo(content: String) {
            _memos.add(MemoItemLeaf(content, false))
        }
    }
