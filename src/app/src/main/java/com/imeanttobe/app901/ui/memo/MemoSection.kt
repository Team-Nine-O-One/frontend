package com.imeanttobe.app901.ui.memo

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.imeanttobe.app901.ui.memo.component.MemoCardList

@Composable
fun MemoSection(viewModel: MemoSectionViewModel = hiltViewModel()) {
    val scrollState = rememberScrollState()
    val memos by viewModel.memos.collectAsState()

    Column(
        verticalArrangement = Arrangement.spacedBy(4.dp),
        modifier =
            Modifier
                .verticalScroll(scrollState)
                .padding(vertical = 8.dp),
    ) {
        MemoCardList(
            memoItems = memos,
            memoStateHolder = viewModel.memoStateHolder,
        )
    }
}

@Preview
@Composable
private fun MemoSectionPreview() {
    MemoSection()
}
