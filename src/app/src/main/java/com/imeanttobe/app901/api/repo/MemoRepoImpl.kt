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

        override suspend fun addMemoLeaf(content: String): ProtoMemoItem {
            val newMemoItemLeaf =
                ProtoMemoItem
                    .newBuilder()
                    .setIsLeaf(true)
                    .setId(idGenerator.assignId())
                    .setContent(content.trim().replace("\n", ""))
                    .build()

            dataStore.updateData { memoListItem ->
                memoListItem
                    .toBuilder()
                    .addItems(newMemoItemLeaf)
                    .build()
            }

            return newMemoItemLeaf
        }

        override suspend fun addMemoGroup(
            title: String,
            contents: List<String>,
        ): ProtoMemoItem {
            val newMemoItemGroup =
                ProtoMemoItem
                    .newBuilder()
                    .setIsLeaf(false)
                    .setId(idGenerator.assignId())
                    .setContent(title)
                    .addAllItems(
                        contents.map { content ->
                            ProtoMemoItem
                                .newBuilder()
                                .setId(idGenerator.assignId())
                                .setIsLeaf(true)
                                .setContent(content.trim().replace("", ""))
                                .build()
                        },
                    ).build()

            dataStore.updateData { memoListItem ->
                memoListItem
                    .toBuilder()
                    .addItems(newMemoItemGroup)
                    .build()
            }

            return newMemoItemGroup
        }

        override suspend fun editMemo(
            itemToEdit: ProtoMemoItem,
            newContent: String,
        ): ProtoMemoItem? {
            var editedProtoItem: ProtoMemoItem? = null

            dataStore.updateData { currentMemoItemList ->
                val itemIndex = currentMemoItemList.itemsList.indexOfFirst { it.id == itemToEdit.id }

                if (itemIndex != -1) {
                    val oldItem = currentMemoItemList.itemsList[itemIndex]
                    val updatedItem =
                        oldItem
                            .toBuilder()
                            .setContent(newContent.trim().replace("\n", "")) // Apply same trimming as in addMemoLeaf
                            .build()
                    editedProtoItem = updatedItem // Store for returning

                    currentMemoItemList
                        .toBuilder()
                        .setItems(itemIndex, updatedItem) // Replace the item at the specific index
                        .build()
                } else {
                    // Item not found, return current state
                    currentMemoItemList
                }
            }
            return editedProtoItem
        }

        override suspend fun removeMemo(memoId: Long) {
            dataStore.updateData { memoListItem ->
                memoListItem
                    .toBuilder()
                    .clearItems()
                    .addAllItems(
                        memoListItem.itemsList.filter { item ->
                            item.id != memoId
                        },
                    ).build()
            }
        }
    }
