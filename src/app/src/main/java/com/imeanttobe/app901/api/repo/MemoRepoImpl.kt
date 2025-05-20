package com.imeanttobe.app901.api.repo

import android.content.Context
import com.imeanttobe.app901.MemoItem
import com.imeanttobe.app901.api.serializer.memoItemListDataStore
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class MemoRepoImpl
    @Inject
    constructor(
        private val context: Context,
    ) : MemoRepo {
        private val dataStore = context.memoItemListDataStore

        val getMemosFlow: Flow<List<MemoItem>> =
            context.memoItemListDataStore.data.map { memoListItem ->
                memoListItem.itemsList
            }

        override suspend fun saveMemos(memos: List<MemoItem>) {
            dataStore.updateData { memoListItem ->
                memoListItem
                    .toBuilder()
                    .clearItems()
                    .addAllItems(memos)
                    .build()
            }
        }

        override suspend fun addMemo(memo: MemoItem) {
            dataStore.updateData { memoListItem ->
                memoListItem
                    .toBuilder()
                    .addItems(memo)
                    .build()
            }
        }

        override suspend fun removeMemo(memoId: Long) {
            dataStore.updateData { memoListItem ->
                memoListItem
                    .toBuilder()
                    .clearItems()
                    .addAllItems(
                        memoListItem.itemsList.filter { memoItem ->
                            memoItem.id != memoId
                        },
                    ).build()
            }
        }
    }
