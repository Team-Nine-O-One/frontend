package com.imeanttobe.app901.ui.memo.component

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.imeanttobe.app901.ProtoMemoItem
import com.imeanttobe.app901.ui.memo.MemoSection
import kotlin.collections.forEach

@Composable
fun MemoCardList(
    memoItems: List<ProtoMemoItem>,
    isChecked: (id: Long) -> Boolean,
    setChecked: (id: Long, value: Boolean) -> Unit,
    modifier: Modifier = Modifier,
) {
    Column(
        verticalArrangement = Arrangement.spacedBy(16.dp),
        modifier = Modifier.then(modifier),
    ) {
        memoItems.forEach { item ->
            if (item.isLeaf) {
                MemoLeafCard(
                    item = item,
                    // checked = memoStateHolder.isChecked(item.id),
                    checked = isChecked(item.id),
                    onCheckedChange = { id, newValue ->
                        // memoStateHolder.setChecked(id, newValue)
                        setChecked(id, newValue)
                    },
                )
            } else {
                MemoGroupCard(
                    item = item,
                    // checked = memoStateHolder.isChecked(item.id),
                    checked = isChecked(item.id),
                    onCheckedChange = { id, newValue ->
                        // memoStateHolder.setChecked(id, newValue)
                        setChecked(id, newValue)
                    },
                )
            }
        }
    }
}

@Preview
@Composable
private fun MemoCardListPreview() {
    MemoSection()
}
