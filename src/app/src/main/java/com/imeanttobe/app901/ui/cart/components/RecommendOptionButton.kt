package com.imeanttobe.app901.ui.cart.components

import androidx.compose.foundation.layout.width
import androidx.compose.material3.SegmentedButton
import androidx.compose.material3.SegmentedButtonDefaults
import androidx.compose.material3.SingleChoiceSegmentedButtonRow
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Composable
fun RecommendOptionButton(
    currentIndex: Int,
    onChangeIndex: (Int) -> Unit
) {
    val options = listOf("거리 우선", "균형", "가격 우선")

    SingleChoiceSegmentedButtonRow {
        options.forEachIndexed { index, option ->
            SegmentedButton(
                selected = index == currentIndex,
                onClick = { onChangeIndex(index) },
                shape = SegmentedButtonDefaults.itemShape(
                    index = index,
                    count = options.size
                ),
                label = { Text(option) },
                modifier = Modifier.width(120.dp)
            )
        }
    }
}

@Preview
@Composable
fun RecommendOptionButtonPreview() {
    RecommendOptionButton(
        currentIndex = 0,
        onChangeIndex = {}
    )
}