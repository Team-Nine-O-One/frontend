package com.imeanttobe.app901.ui.memo.component

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Error
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.imeanttobe.app901.ProtoMemoItem
import com.imeanttobe.app901.ui.component.IconAndText
import com.imeanttobe.app901.ui.memo.MemoSection
import kotlin.collections.forEach

@Composable
fun MemoCardList(
    memoItems: List<ProtoMemoItem>,
    isChecked: (id: Long) -> Boolean,
    setChecked: (id: Long, value: Boolean) -> Unit,
    modifier: Modifier = Modifier,
) {
    if (memoItems.isNotEmpty()) {
        Column(
            verticalArrangement = Arrangement.spacedBy(16.dp),
            modifier = Modifier.then(modifier),
        ) {
            memoItems.forEach { item ->
                if (item.isLeaf) {
                    MemoLeafCard(
                        item = item,
                        // checked = memoStateHolder.isChecked(item.id),
                        checked = isChecked(item.id),
                        onCheckedChange = { id, newValue ->
                            // memoStateHolder.setChecked(id, newValue)
                            setChecked(id, newValue)
                        },
                    )
                } else {
                    MemoGroupCard(
                        item = item,
                        // checked = memoStateHolder.isChecked(item.id),
                        checked = isChecked(item.id),
                        onCheckedChange = { id, newValue ->
                            // memoStateHolder.setChecked(id, newValue)
                            setChecked(id, newValue)
                        },
                    )
                }
            }
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
private fun MemoCardListPreview() {
    MemoSection()
}
