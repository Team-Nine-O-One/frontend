package com.imeanttobe.app901.ui.memo.component

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.imeanttobe.app901.data.type.MemoItem
import com.imeanttobe.app901.data.type.MemoItemGroup
import com.imeanttobe.app901.data.type.MemoItemLeaf
import com.imeanttobe.app901.ui.memo.MemoSection
import kotlin.collections.forEach

@Composable
fun MemoCardList(
    memoItems: List<MemoItem>,
    modifier: Modifier = Modifier,
) {
    Column(
        verticalArrangement = Arrangement.spacedBy(16.dp),
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
    MemoSection()
}
