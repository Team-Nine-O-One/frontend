package com.imeanttobe.app901.ui.memo

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Error
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.imeanttobe.app901.R
import com.imeanttobe.app901.ui.component.DeleteMemoDialog
import com.imeanttobe.app901.ui.component.IconAndText
import com.imeanttobe.app901.ui.memo.component.MemoCardList

@Composable
fun MemoSection(viewModel: MemoSectionViewModel) {
    val memos by viewModel.memos.collectAsState()

    LaunchedEffect(key1 = null) {
        viewModel.putMemosInMap()
    }

    if (memos.isNotEmpty()) {
        Column(
            verticalArrangement = Arrangement.spacedBy(8.dp),
            modifier = Modifier.padding(horizontal = 16.dp),
        ) {
            // Items
            MemoCardList(
                memoItems = memos,
                isChecked = { item -> viewModel.isChecked(item) },
                setChecked = { item, value -> viewModel.setChecked(item, value) },
                onDelete = { item -> viewModel.deleteMemo(item) },
                onEdit = { item, newContent -> viewModel.editMemo(item, newContent) },
                onEditInGroup = { parent, item, newContent -> viewModel.editMemoLeafInGroup(parent, item, newContent) },
                onToggleGroup = { item, newValue -> viewModel.onToggleGroup(item, newValue) },
                onDeleteInGroup = { parent, itemToRemove -> viewModel.deleteMemoLeafInGroup(parent, itemToRemove) },
                modifier = Modifier,
            )
        }

        // Prints dialog when user requests to delete specific memo
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
