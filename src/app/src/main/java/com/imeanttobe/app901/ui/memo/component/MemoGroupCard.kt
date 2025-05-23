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
    checked: Boolean,
    onCheckedChange: (Long, Boolean) -> Unit,
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
            modifier = Modifier.padding(4.dp),
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier.padding(4.dp),
            ) {
                Checkbox(
                    checked = checked,
                    onCheckedChange = { newValue ->
                        {
                            onCheckedChange(item.id, newValue)
                            if (newValue == false) {
                                item.itemsList.forEach { leaf ->
                                    onCheckedChange(leaf.id, false)
                                }
                            }
                        }
                    },
                )

                Text(
                    text = item.content,
                    style = MaterialTheme.typography.titleLarge,
                    modifier = Modifier.weight(1f),
                )

                IconButton(
                    onClick = {},
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
                    checked = checked,
                    onCheckedChange = { _, newValue ->
                        {
                            onCheckedChange(leaf.id, newValue)
                            if (newValue == false) {
                                onCheckedChange(item.id, false)
                            }
                        }
                    },
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
        checked = false,
        onCheckedChange = { _, _ -> },
    )
}
