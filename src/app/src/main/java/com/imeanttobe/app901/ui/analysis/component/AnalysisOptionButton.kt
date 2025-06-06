package com.imeanttobe.app901.ui.analysis.component

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.size
import androidx.compose.material3.ButtonGroupDefaults
import androidx.compose.material3.ExperimentalMaterial3ExpressiveApi
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.material3.ToggleButton
import androidx.compose.material3.ToggleButtonDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.semantics.Role
import androidx.compose.ui.semantics.role
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.tooling.preview.Preview
import com.imeanttobe.app901.data.enum.AnalysisOption

@OptIn(ExperimentalMaterial3ExpressiveApi::class)
@Composable
fun AnalysisOptionButton(
    selectedOption: AnalysisOption,
    onChangeOption: (AnalysisOption) -> Unit,
    modifier: Modifier = Modifier,
) {
    Row(
        modifier = modifier,
        horizontalArrangement = Arrangement.spacedBy(ButtonGroupDefaults.ConnectedSpaceBetween),
    ) {
        AnalysisOption.entries.forEach { option ->
            ToggleButton(
                checked = selectedOption == option,
                onCheckedChange = { onChangeOption(option) },
                modifier =
                    Modifier
                        .weight(if (selectedOption == option) 1.5f else 1f)
                        .semantics { role = Role.RadioButton },
                shapes =
                    when (option) {
                        AnalysisOption.DISTANCE -> ButtonGroupDefaults.connectedLeadingButtonShapes()
                        AnalysisOption.PRICE -> ButtonGroupDefaults.connectedTrailingButtonShapes()
                        AnalysisOption.BEST -> ButtonGroupDefaults.connectedMiddleButtonShapes()
                    },
            ) {
                Icon(
                    imageVector = if (selectedOption == option) option.checkedIcon else option.uncheckedIcon,
                    contentDescription = null,
                )
                Spacer(modifier = Modifier.size(ToggleButtonDefaults.IconSpacing))
                Text(text = stringResource(option.stringResId))
            }
        }
    }
}

@Preview
@Composable
fun DistanceOptionButtonPreview() {
    AnalysisOptionButton(
        selectedOption = AnalysisOption.DISTANCE,
        onChangeOption = {},
    )
}

@Preview
@Composable
fun BestOptionButtonPreview() {
    AnalysisOptionButton(
        selectedOption = AnalysisOption.BEST,
        onChangeOption = {},
    )
}

@Preview
@Composable
fun PriceOptionButtonPreview() {
    AnalysisOptionButton(
        selectedOption = AnalysisOption.PRICE,
        onChangeOption = {},
    )
}
