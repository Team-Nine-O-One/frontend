package com.imeanttobe.app901.ui.home.component.memo

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Edit
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.imeanttobe.app901.data.type.MemoItem
import com.imeanttobe.app901.data.type.MemoItemGroup
import com.imeanttobe.app901.data.type.MemoItemLeaf

@Composable
fun MemoSection(memoItems: List<MemoItem>) {
    val scrollState = rememberScrollState()

    Column(
        verticalArrangement = Arrangement.spacedBy(4.dp),
        modifier =
            Modifier
                .verticalScroll(scrollState)
                .padding(vertical = 4.dp),
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.padding(horizontal = 8.dp),
        ) {
            Text(
                text = "장바구니 제목",
                style = MaterialTheme.typography.headlineMedium,
                fontWeight = FontWeight.Bold,
            )

            IconButton(
                onClick = {},
            ) {
                Icon(
                    imageVector = Icons.Rounded.Edit,
                    contentDescription = null,
                )
            }
        }

        MemoCardList(memoItems = memoItems)
    }
}

@Preview
@Composable
private fun MemoSectionPreview() {
    MemoSection(
        memoItems =
            listOf(
                MemoItemLeaf("Item 1", false),
                MemoItemLeaf("Item 2", true),
                MemoItemGroup(
                    content = "Group 1",
                    checked = false,
                    items =
                        mutableListOf(
                            MemoItemLeaf("Item 3", false),
                            MemoItemLeaf("Item 4", true),
                        ),
                ),
                MemoItemLeaf("Item 5", false),
            ),
    )
}
