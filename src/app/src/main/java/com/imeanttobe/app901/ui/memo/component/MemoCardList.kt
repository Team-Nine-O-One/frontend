package com.imeanttobe.app901.ui.memo.component

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Delete
import androidx.compose.material3.Checkbox
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.imeanttobe.app901.ProtoMemoItem
import com.imeanttobe.app901.ui.component.DeleteAllMemosDialog
import com.imeanttobe.app901.ui.memo.MemoSection
import kotlin.collections.forEach

@Composable
fun MemoCardList(
    memoItems: List<ProtoMemoItem>,
    isChecked: (id: Long) -> Boolean,
    setChecked: (id: Long, value: Boolean) -> Unit,
    onDelete: (item: ProtoMemoItem) -> Unit,
    dialogState: Boolean,
    setDialogState: (value: Boolean) -> Unit,
    modifier: Modifier = Modifier,
) {
    Column(
        verticalArrangement = Arrangement.spacedBy(16.dp),
        modifier = Modifier.then(modifier),
    ) {
        Row(
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.fillMaxWidth(),
        ) {
            Checkbox(
                checked = memoItems.all { item -> isChecked(item.id) },
                onCheckedChange = { newValue -> memoItems.forEach { item -> setChecked(item.id, newValue) } },
            )

            IconButton(
                onClick = { setDialogState(true) },
            ) {
                Icon(
                    imageVector = Icons.Rounded.Delete,
                    contentDescription = null,
                )
            }
        }

        memoItems.forEach { item ->
            if (item.isLeaf) {
                MemoLeafCard(
                    item = item,
                    checked = isChecked(item.id),
                    onCheckedChange = { id, newValue -> setChecked(id, newValue) },
                    onDelete = onDelete,
                )
            } else {
                MemoGroupCard(
                    item = item,
                    checked = isChecked(item.id),
                    onCheckedChange = { id, newValue -> setChecked(id, newValue) },
                    onDelete = onDelete,
                )
            }
        }
    }

    if (dialogState) {
        DeleteAllMemosDialog(
            onDismiss = { setDialogState(false) },
            onConfirm = { memoItems.forEach { item -> onDelete(item) } },
        )
    }
}

@Preview
@Composable
private fun MemoCardListPreview() {
    MemoSection()
}
