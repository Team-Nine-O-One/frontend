package com.imeanttobe.app901.ui.memo.component

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.state.ToggleableState
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.imeanttobe.app901.ProtoMemoItem
import com.imeanttobe.app901.ui.memo.MemoSection
import kotlin.collections.forEach

@Composable
fun MemoCardList(
    memoItems: List<ProtoMemoItem>,
    isChecked: (item: ProtoMemoItem) -> ToggleableState,
    setChecked: (item: ProtoMemoItem, value: Boolean) -> Unit,
    onDelete: (item: ProtoMemoItem) -> Unit,
    onEdit: (item: ProtoMemoItem, newContent: String) -> Unit,
    onToggleGroup: (ProtoMemoItem, Boolean) -> Unit,
    onDeleteMemoLeafInGroup: (ProtoMemoItem, ProtoMemoItem) -> Unit,
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
                    isChecked = isChecked,
                    setChecked = setChecked,
                    onDelete = onDelete,
                )
            } else {
                MemoGroupCard(
                    item = item,
                    isChecked = isChecked,
                    setChecked = setChecked,
                    onDelete = onDelete,
                    onToggleGroup = onToggleGroup,
                    onDeleteMemoLeafInGroup = onDeleteMemoLeafInGroup,
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
