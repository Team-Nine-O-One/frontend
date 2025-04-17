package com.imeanttobe.app901.ui.cart.components

import androidx.compose.foundation.layout.width
import androidx.compose.material3.SegmentedButton
import androidx.compose.material3.SegmentedButtonDefaults
import androidx.compose.material3.SingleChoiceSegmentedButtonRow
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.imeanttobe.app901.ui.cart.MartRecommendOption

@Composable
fun RecommendOptionButton(
    currentOption: MartRecommendOption,
    onChangeOption: (MartRecommendOption) -> Unit
) {
    val options = listOf(
        MartRecommendOption.DISTANCE,
        MartRecommendOption.BALANCED,
        MartRecommendOption.PRICE
    )

    SingleChoiceSegmentedButtonRow {
        options.forEachIndexed { index, option ->
            SegmentedButton(
                selected = option == currentOption,
                onClick = { onChangeOption(option) },
                shape = SegmentedButtonDefaults.itemShape(
                    index = index,
                    count = options.size
                ),
                label = { Text(text = stringResource(option.label)) },
                modifier = Modifier.width(120.dp)
            )
        }
    }
}

@Preview
@Composable
fun RecommendOptionButtonPreview() {
    RecommendOptionButton(
        currentOption = MartRecommendOption.BALANCED,
        onChangeOption = {}
    )
}