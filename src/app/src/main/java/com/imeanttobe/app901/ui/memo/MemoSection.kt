package com.imeanttobe.app901.ui.memo

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.imeanttobe.app901.data.type.MemoItemGroup
import com.imeanttobe.app901.data.type.MemoItemLeaf
import com.imeanttobe.app901.ui.memo.component.MemoCardList

@Composable
fun MemoSection(viewModel: MemoSectionViewModel = hiltViewModel()) {
    val scrollState = rememberScrollState()

    Column(
        verticalArrangement = Arrangement.spacedBy(4.dp),
        modifier =
            Modifier
                .verticalScroll(scrollState)
                .padding(vertical = 8.dp),
    ) {
        MemoCardList(memoItems = viewModel.memos)
    }
}

@Preview
@Composable
private fun MemoSectionPreview() {
    MemoSection()
}
