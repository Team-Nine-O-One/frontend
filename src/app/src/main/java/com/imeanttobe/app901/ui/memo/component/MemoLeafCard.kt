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
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.imeanttobe.app901.data.type.MemoItemLeaf

@Composable
fun MemoLeafCard(
    item: MemoItemLeaf,
    modifier: Modifier = Modifier,
) {
    var checked by remember { mutableStateOf(item.isChecked()) }

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
                checked = checked,
                onCheckedChange = {
                    checked = it
                    item.setChecked(it)
                },
            )

            Text(
                text = item.getContent(),
                modifier = Modifier.weight(1f),
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
                    onClick = {},
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
        item =
            MemoItemLeaf("Hello World", false),
    )
}
