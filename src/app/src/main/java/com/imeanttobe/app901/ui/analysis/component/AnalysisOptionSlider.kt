package com.imeanttobe.app901.ui.analysis.component

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExperimentalMaterial3ExpressiveApi
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Slider
import androidx.compose.material3.SliderDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.rememberSliderState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.snapshotFlow
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.imeanttobe.app901.data.enum.AnalysisOption
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.flow.filter

@OptIn(ExperimentalMaterial3Api::class, ExperimentalMaterial3ExpressiveApi::class)
@Composable
fun AnalysisOptionSlider(
    selectedOption: AnalysisOption,
    onChangeOption: (AnalysisOption) -> Unit,
    modifier: Modifier = Modifier,
) {
    val context = LocalContext.current
    val sliderState =
        rememberSliderState(
            valueRange = -1f..1f,
            value =
                when (selectedOption) {
                    AnalysisOption.DISTANCE -> -1f
                    AnalysisOption.PRICE -> 1f
                    AnalysisOption.BEST -> 0f
                },
            steps = 1,
        )

    // More robust way: Use snapshotFlow to react when dragging stops or value settles
    LaunchedEffect(sliderState, selectedOption, onChangeOption) {
        snapshotFlow { sliderState.value }
            .distinctUntilChanged() // Only emit when the value truly changes
            .filter { !sliderState.isDragging } // Only proceed if the user is NOT currently dragging
            .collect { settledValue ->
                val newOption =
                    when (settledValue) {
                        -1f -> AnalysisOption.DISTANCE
                        0f -> AnalysisOption.BEST
                        1f -> AnalysisOption.PRICE
                        else -> null // Should not happen
                    }
                newOption?.let {
                    if (it != selectedOption) {
                        onChangeOption(it)
                    }
                }
            }
    }

    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(8.dp),
        modifier = modifier,
    ) {
        Row(
            horizontalArrangement = Arrangement.Absolute.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically,
            modifier =
                Modifier
                    .fillMaxWidth(),
        ) {
            AnalysisOption.entries.forEach { option ->
                Row(
                    horizontalArrangement = Arrangement.spacedBy(8.dp),
                    verticalAlignment = Alignment.CenterVertically,
                ) {
                    Icon(
                        imageVector = option.checkedIcon,
                        contentDescription = context.getString(option.stringResId),
                        modifier = Modifier.size(20.dp),
                    )
                    Text(
                        text = stringResource(option.stringResId),
                        style = MaterialTheme.typography.bodyMedium,
                    )
                }
            }
        }
        Slider(
            state = sliderState,
            track = { SliderDefaults.CenteredTrack(sliderState = sliderState) },
            modifier = Modifier.fillMaxWidth(),
        )
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Preview
@Composable
fun DistancePriceSliderPreview() {
    AnalysisOptionSlider(
        selectedOption = AnalysisOption.DISTANCE,
        onChangeOption = {},
        modifier = Modifier.padding(16.dp),
    )
}
