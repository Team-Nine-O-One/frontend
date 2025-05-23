package com.imeanttobe.app901.api.repo

import android.content.Context
import com.imeanttobe.app901.ProtoMemoItem
import com.imeanttobe.app901.api.serializer.memoItemListDataStore
import com.imeanttobe.app901.data.type.IdGenerator
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class MemoRepoImpl
    @Inject
    constructor(
        private val context: Context,
        private val idGenerator: IdGenerator,
    ) : MemoRepo {
        private val dataStore = context.memoItemListDataStore

        override val getMemosFlow: Flow<List<ProtoMemoItem>> =
            dataStore.data.map { memoItemList -> memoItemList.itemsList }

        override suspend fun saveMemos(memos: List<ProtoMemoItem>) {
            dataStore.updateData { memoListItem ->
                memoListItem
                    .toBuilder()
                    .clearItems()
                    .addAllItems(memos)
                    .build()
            }
        }

        override suspend fun addMemo(content: String): ProtoMemoItem {
            val newMemoItemLeaf =
                ProtoMemoItem
                    .newBuilder()
                    .setIsLeaf(true)
                    .setId(idGenerator.assignId())
                    .setContent(content)
                    .build()

            dataStore.updateData { memoListItem ->
                memoListItem
                    .toBuilder()
                    .addItems(newMemoItemLeaf)
                    .build()
            }

            return newMemoItemLeaf
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
