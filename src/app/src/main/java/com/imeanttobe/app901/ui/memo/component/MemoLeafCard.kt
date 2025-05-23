package com.imeanttobe.app901.ui.memo.component

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Delete
import androidx.compose.material.icons.rounded.Edit
import androidx.compose.material3.Checkbox
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedCard
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.imeanttobe.app901.ProtoMemoItem

@Composable
fun MemoLeafCard(
    item: ProtoMemoItem,
    getChecked: (Long) -> Boolean,
    onCheckedChange: (ProtoMemoItem, Boolean) -> Unit,
    onDelete: (ProtoMemoItem) -> Unit,
    modifier: Modifier = Modifier,
) {
    OutlinedCard(
        modifier =
            Modifier
                .fillMaxWidth()
                .then(modifier),
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.padding(4.dp),
        ) {
            Checkbox(
                checked = getChecked(item.id),
                onCheckedChange = { newValue -> onCheckedChange(item, newValue) },
            )

            Text(
                text = item.content,
                modifier = Modifier.weight(1f),
                maxLines = 1,
                overflow = TextOverflow.Ellipsis,
            )

            Row {
                IconButton(
                    onClick = {},
                ) {
                    Icon(
                        imageVector = Icons.Rounded.Edit,
                        contentDescription = null,
                    )
                }
                IconButton(
                    onClick = { onDelete(item) },
                ) {
                    Icon(
                        imageVector = Icons.Rounded.Delete,
                        contentDescription = null,
                    )
                }
            }
        }
    }
}

@Preview
@Composable
private fun MemoLeafCardPreview() {
    MemoLeafCard(
        item = ProtoMemoItem.getDefaultInstance(),
        getChecked = { false },
        onCheckedChange = { _, _ -> },
        onDelete = {},
    )
}
