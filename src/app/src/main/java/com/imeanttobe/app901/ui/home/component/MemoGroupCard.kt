package com.imeanttobe.app901.ui.home.component

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
import com.imeanttobe.app901.data.type.MemoItemGroup
import com.imeanttobe.app901.data.type.MemoItemLeaf

@Composable
fun MemoGroupCard(
    item: MemoItemGroup,
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
                    checked = item.isChecked(),
                    onCheckedChange = { value ->
                        {
                            item.setChecked(value)
                            item.getItems().forEach { leaf -> leaf.setChecked(value) }
                        }
                    },
                )

                Text(
                    text = item.getContent(),
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

            item.getItems().forEach { leaf ->
                MemoLeafCard(item = leaf)
            }
        }
    }
}

@Preview
@Composable
private fun MemoGroupCardPreview() {
    MemoGroupCard(
        item =
            MemoItemGroup(
                content = "Hello World",
                checked = false,
                items =
                    mutableListOf(
                        MemoItemLeaf("Hello World", false),
                        MemoItemLeaf("Hello World", false),
                        MemoItemLeaf("Hello World", false),
                    ),
            ),
    )
}
