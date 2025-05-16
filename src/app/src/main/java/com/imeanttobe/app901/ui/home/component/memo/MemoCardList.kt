package com.imeanttobe.app901.ui.home.component.memo

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.imeanttobe.app901.data.type.MemoItem
import com.imeanttobe.app901.data.type.MemoItemGroup
import com.imeanttobe.app901.data.type.MemoItemLeaf
import kotlin.collections.forEach

@Composable
fun MemoCardList(
    memoItems: List<MemoItem>,
    modifier: Modifier = Modifier,
) {
    Column(
        verticalArrangement = Arrangement.spacedBy(4.dp),
        modifier = Modifier.then(modifier),
    ) {
        memoItems.forEach { item ->
            if (item is MemoItemLeaf) {
                MemoLeafCard(item = item)
            } else {
                MemoGroupCard(item = item as MemoItemGroup)
            }
        }
    }
}

@Preview
@Composable
private fun MemoCardListPreview() {
    MemoSection(
        memoItems =
            listOf(
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
            ),
    )
}
