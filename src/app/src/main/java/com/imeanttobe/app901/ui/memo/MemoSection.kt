package com.imeanttobe.app901.ui.memo

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Error
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CheckboxDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TriStateCheckbox
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.imeanttobe.app901.R
import com.imeanttobe.app901.ui.component.DeleteMemoDialog
import com.imeanttobe.app901.ui.component.IconAndText
import com.imeanttobe.app901.ui.memo.component.MemoCardList

@Composable
fun MemoSection(viewModel: MemoSectionViewModel = hiltViewModel()) {
    val scrollState = rememberScrollState()
    val memos by viewModel.memos.collectAsState()

    LaunchedEffect(key1 = null) {
        viewModel.putMemosInMap()
    }

    if (memos.isNotEmpty()) {
        Column(
            verticalArrangement = Arrangement.spacedBy(4.dp),
            modifier =
                Modifier
                    .verticalScroll(scrollState)
                    .padding(horizontal = 8.dp),
        ) {
            Row(
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier.fillMaxWidth(),
            ) {
                TextButton(onClick = { viewModel.onToggleOverall() }) {
                    TriStateCheckbox(
                        state = viewModel.getOverallToggleState(),
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
                    onClick = { viewModel.setDeleteAllMemosDialogState(true) },
                ) {
                    Text(
                        text =
                            stringResource(
                                if (viewModel.isAllUncheckedState.value) {
                                    R.string.delete_all
                                } else {
                                    R.string.delete_selected
                                },
                            ),
                    )
                }
            }

            MemoCardList(
                memoItems = memos,
                isChecked = { item -> viewModel.isChecked(item) },
                setChecked = { item, value -> viewModel.setChecked(item, value) },
                onDelete = { item -> viewModel.deleteMemo(item) },
                onEdit = { item, newContent -> viewModel.editMemo(item, newContent) },
                onEditInGroup = { parent, item, newContent -> viewModel.editMemoLeafInGroup(parent, item, newContent) },
                onToggleGroup = { item, newValue -> viewModel.onToggleGroup(item, newValue) },
                onDeleteInGroup = { parent, itemToRemove -> viewModel.deleteMemoLeafInGroup(parent, itemToRemove) },
            )
        }

        if (viewModel.deleteAllMemosDialogState.value) {
            DeleteMemoDialog(
                isAllUnchecked = viewModel.isAllUncheckedState.value,
                onDismiss = { viewModel.setDeleteAllMemosDialogState(false) },
                onConfirm = { viewModel.deleteCheckedMemos() },
            )
        }
    } else {
        IconAndText(
            icon = Icons.Rounded.Error,
            text = stringResource(R.string.error_no_items_memo),
            contentDescription = "Empty memo",
            modifier = Modifier.fillMaxSize(),
        )
    }
}

@Preview
@Composable
private fun MemoSectionPreview() {
    MemoSection()
}
