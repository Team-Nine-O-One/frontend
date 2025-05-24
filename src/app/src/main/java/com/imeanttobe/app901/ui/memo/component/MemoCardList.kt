package com.imeanttobe.app901.ui.memo.component

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.width
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CheckboxDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TriStateCheckbox
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.state.ToggleableState
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.imeanttobe.app901.ProtoMemoItem
import com.imeanttobe.app901.R
import com.imeanttobe.app901.ui.component.DeleteMemoDialog
import com.imeanttobe.app901.ui.memo.MemoSection
import kotlin.collections.forEach

@Composable
fun MemoCardList(
    memoItems: List<ProtoMemoItem>,
    isChecked: (id: Long) -> Boolean,
    setChecked: (item: ProtoMemoItem, value: Boolean) -> Unit,
    onDelete: (item: ProtoMemoItem) -> Unit,
    onEdit: (item: ProtoMemoItem, newContent: String) -> Unit,
    dialogState: Boolean,
    setDialogState: (value: Boolean) -> Unit,
    modifier: Modifier = Modifier,
) {
    val isAllChecked = memoItems.all { item -> isChecked(item.id) }
    val isAllUnchecked = memoItems.none { item -> isChecked(item.id) }
    val isSomeChecked = memoItems.any { item -> isChecked(item.id) }
    val toggleableState =
        when {
            isAllChecked -> ToggleableState.On
            isAllUnchecked -> ToggleableState.Off
            isSomeChecked -> ToggleableState.Indeterminate
            else -> ToggleableState.Off
        }

    fun calculateToggleableState() {
        val newValue =
            when (toggleableState) {
                ToggleableState.On -> false
                ToggleableState.Off -> true
                ToggleableState.Indeterminate -> null
            }
        if (newValue != null) {
            memoItems.forEach { item ->
                setChecked(item, newValue)
                if (!item.isLeaf) {
                    item.itemsList.forEach { leaf ->
                        setChecked(leaf, newValue)
                    }
                }
            }
        }
    }

    Column(
        verticalArrangement = Arrangement.spacedBy(16.dp),
        modifier = Modifier.then(modifier),
    ) {
        Row(
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.fillMaxWidth(),
        ) {
            TextButton(
                onClick = { calculateToggleableState() },
            ) {
                TriStateCheckbox(
                    state = toggleableState,
                    onClick = null,
                    colors =
                        CheckboxDefaults.colors(
                            uncheckedColor = ButtonDefaults.textButtonColors().contentColor,
                        ),
                )
                Spacer(modifier = Modifier.width(8.dp))
                Text(text = stringResource(R.string.select_all))
            }

            TextButton(
                onClick = { setDialogState(true) },
            ) {
                Text(text = stringResource(if (isAllUnchecked) R.string.delete_all else R.string.delete_selected))
            }
        }

        memoItems.forEach { item ->
            if (item.isLeaf) {
                MemoLeafCard(
                    item = item,
                    getChecked = isChecked,
                    onCheckedChange = setChecked,
                    onDelete = onDelete,
                )
            } else {
                MemoGroupCard(
                    item = item,
                    getChecked = isChecked,
                    onCheckedChange = setChecked,
                    onDelete = onDelete,
                )
            }
        }
    }

    if (dialogState) {
        DeleteMemoDialog(
            isAllUnchecked = isAllUnchecked,
            onDismiss = { setDialogState(false) },
            onConfirm = {
                if (isAllUnchecked) {
                    memoItems.forEach { item -> onDelete(item) }
                } else {
                    memoItems.forEach { item ->
                        if (isChecked(item.id)) {
                            onDelete(item)
                        }
                    }
                }
            },
        )
    }
}

@Preview
@Composable
private fun MemoCardListPreview() {
    MemoSection()
}
