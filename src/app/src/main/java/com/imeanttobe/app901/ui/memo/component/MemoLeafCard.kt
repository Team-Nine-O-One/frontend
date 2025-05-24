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
import androidx.compose.ui.state.ToggleableState
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.imeanttobe.app901.ProtoMemoItem
import com.imeanttobe.app901.ui.component.EditMemoDialog

@Composable
fun MemoLeafCard(
    item: ProtoMemoItem,
    isChecked: (ProtoMemoItem) -> ToggleableState,
    setChecked: (ProtoMemoItem, Boolean) -> Unit,
    onDelete: (ProtoMemoItem) -> Unit,
    onEdit: (ProtoMemoItem, String) -> Unit,
    modifier: Modifier = Modifier,
) {
    var dialogState by remember { mutableStateOf(false) }
    var dialogTextFieldContent by remember { mutableStateOf(item.content) }

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
                checked = if (isChecked(item) == ToggleableState.On) true else false,
                onCheckedChange = { newValue -> setChecked(item, newValue) },
            )

            Text(
                text = item.content,
                modifier = Modifier.weight(1f),
                maxLines = 1,
                overflow = TextOverflow.Ellipsis,
            )

            Row {
                IconButton(
                    onClick = { dialogState = true },
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

    if (dialogState) {
        EditMemoDialog(
            textFieldContent = dialogTextFieldContent,
            onTextFieldChange = { newValue -> dialogTextFieldContent = newValue },
            onDismiss = { dialogState = false },
            onConfirm = { onEdit(item, dialogTextFieldContent) },
        )
    }
}

@Preview
@Composable
private fun MemoLeafCardPreview() {
    MemoLeafCard(
        item = ProtoMemoItem.getDefaultInstance(),
        isChecked = { ToggleableState.Off },
        setChecked = { _, _ -> },
        onDelete = {},
        onEdit = { _, _ -> },
    )
}
