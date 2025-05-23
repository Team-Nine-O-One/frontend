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
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.imeanttobe.app901.ProtoMemoItem

@Composable
fun MemoGroupCard(
    item: ProtoMemoItem,
    getChecked: (Long) -> Boolean,
    onCheckedChange: (ProtoMemoItem, Boolean) -> Unit,
    onDelete: (ProtoMemoItem) -> Unit,
    modifier: Modifier = Modifier,
) {
    val onLeafDelete = { leaf: ProtoMemoItem ->
        onDelete(leaf)
        if (item.itemsList.isEmpty()) {
            onDelete(item)
        }
    }
    val onLeafCheckedChange = { leaf: ProtoMemoItem, value: Boolean ->
        onCheckedChange(leaf, value)
        if (item.itemsList.all { leaf -> getChecked(leaf.id) }) {
            onCheckedChange(item, true)
        } else {
            onCheckedChange(item, false)
        }
    }

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
                    checked = getChecked(item.id),
                    onCheckedChange = { newValue ->
                        onCheckedChange(item, newValue)
                        item.itemsList.forEach { leaf ->
                            onCheckedChange(leaf, newValue)
                        }
                    },
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
                    getChecked = getChecked,
                    onCheckedChange = onLeafCheckedChange,
                    onDelete = onLeafDelete,
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
        getChecked = { false },
        onCheckedChange = { _, _ -> {} },
        onDelete = {},
    )
}
