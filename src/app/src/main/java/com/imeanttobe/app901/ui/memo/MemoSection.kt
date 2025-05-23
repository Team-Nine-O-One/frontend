package com.imeanttobe.app901.ui.memo

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Error
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.imeanttobe.app901.ui.component.IconAndText
import com.imeanttobe.app901.ui.memo.component.MemoCardList

@Composable
fun MemoSection(viewModel: MemoSectionViewModel = hiltViewModel()) {
    val scrollState = rememberScrollState()
    val memos by viewModel.memos.collectAsState()

    if (memos.isNotEmpty()) {
        Column(
            verticalArrangement = Arrangement.spacedBy(4.dp),
            modifier =
                Modifier
                    .verticalScroll(scrollState)
                    .padding(vertical = 8.dp),
        ) {
            MemoCardList(
                memoItems = memos,
                isChecked = { id -> viewModel.isChecked(id) },
                setChecked = { id, value -> viewModel.setChecked(id, value) },
                onDelete = { item -> viewModel.removeMemo(item) },
                dialogState = viewModel.deleteAllMemosDialogState.value,
                setDialogState = { value -> viewModel.setDeleteAllMemosDialogState(value) },
            )
        }
    } else {
        IconAndText(
            icon = Icons.Rounded.Error,
            text = "장 볼 목록이 비어 있어요.",
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
