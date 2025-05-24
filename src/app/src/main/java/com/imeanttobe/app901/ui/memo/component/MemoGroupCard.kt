package com.imeanttobe.app901.ui.memo.component

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.Card
import androidx.compose.material3.Checkbox
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.state.ToggleableState
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.imeanttobe.app901.ProtoMemoItem

@Composable
fun MemoGroupCard(
    item: ProtoMemoItem,
    isChecked: (ProtoMemoItem) -> ToggleableState,
    setChecked: (ProtoMemoItem, Boolean) -> Unit,
    onToggleGroup: (ProtoMemoItem, Boolean) -> Unit,
    onDeleteMemoLeafInGroup: (ProtoMemoItem, ProtoMemoItem) -> Unit,
    onDelete: (ProtoMemoItem) -> Unit,
    modifier: Modifier = Modifier,
) {
    Card(
        modifier =
            Modifier
                .fillMaxWidth()
                .then(modifier),
    ) {
        Column(
            verticalArrangement = Arrangement.spacedBy(4.dp),
            modifier = Modifier.padding(8.dp),
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier.padding(0.dp),
            ) {
                Checkbox(
                    checked = if (isChecked(item) == ToggleableState.On) true else false,
                    onCheckedChange = { newValue -> onToggleGroup(item, newValue) },
                )

                Text(
                    text = item.content,
                    style = MaterialTheme.typography.titleLarge,
                    modifier = Modifier.weight(1f),
                )

                IconButton(
                    onClick = {
                        item.itemsList.forEach { leaf ->
                            onDelete(leaf)
                        }
                        onDelete(item)
                    },
                ) {
                    Icon(
                        imageVector = Icons.Default.Delete,
                        contentDescription = null,
                    )
                }
            }

            item.itemsList.forEach { leaf ->
                MemoLeafCard(
                    item = leaf,
                    isChecked = isChecked,
                    setChecked = { subItem, value -> setChecked(subItem, value) },
                    onDelete = { subItem -> onDeleteMemoLeafInGroup(item, subItem) },
                )
            }
        }
    }
}

@Preview
@Composable
private fun MemoGroupCardPreview() {
    MemoGroupCard(
        item = ProtoMemoItem.getDefaultInstance(),
        isChecked = { ToggleableState.Off },
        setChecked = { _, _ -> {} },
        onToggleGroup = { _, _ -> {} },
        onDeleteMemoLeafInGroup = { _, _ -> {} },
        onDelete = {},
    )
}
