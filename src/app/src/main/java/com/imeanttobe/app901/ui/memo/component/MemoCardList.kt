package com.imeanttobe.app901.ui.memo.component

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.state.ToggleableState
import androidx.compose.ui.unit.dp
import com.imeanttobe.app901.ProtoMemoItem

@Composable
fun MemoCardList(
    memoItems: List<ProtoMemoItem>,
    isChecked: (item: ProtoMemoItem) -> ToggleableState,
    setChecked: (item: ProtoMemoItem, value: Boolean) -> Unit,
    onDelete: (item: ProtoMemoItem) -> Unit,
    onEdit: (item: ProtoMemoItem, newContent: String) -> Unit,
    onEditInGroup: (parent: ProtoMemoItem, item: ProtoMemoItem, newContent: String) -> Unit,
    onToggleGroup: (ProtoMemoItem, Boolean) -> Unit,
    onDeleteInGroup: (ProtoMemoItem, ProtoMemoItem) -> Unit,
    modifier: Modifier = Modifier,
) {
    val scrollState = rememberScrollState()

    Column(
        verticalArrangement = Arrangement.spacedBy(16.dp),
        modifier = Modifier.verticalScroll(scrollState).then(modifier),
    ) {
        memoItems.forEachIndexed { index, item ->
            val paddingModifier =
                if (index == 0) {
                    Modifier.padding(top = 16.dp)
                } else if (index == memoItems.size - 1) {
                    Modifier.padding(bottom = 16.dp).padding(bottom = 120.dp)
                } else {
                    Modifier
                }

            Box(modifier = paddingModifier) {
                if (item.isLeaf) {
                    MemoLeafCard(
                        item = item,
                        isChecked = isChecked,
                        setChecked = setChecked,
                        onDelete = onDelete,
                        onEdit = onEdit,
                    )
                } else {
                    MemoGroupCard(
                        item = item,
                        isChecked = isChecked,
                        setChecked = setChecked,
                        onDelete = onDelete,
                        onToggleGroup = onToggleGroup,
                        onDeleteMemoLeafInGroup = onDeleteInGroup,
                        onEditInGroup = onEditInGroup,
                    )
                }
            }
        }
    }
}
