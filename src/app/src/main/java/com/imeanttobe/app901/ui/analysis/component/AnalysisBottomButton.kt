package com.imeanttobe.app901.ui.analysis.component

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Share
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3ExpressiveApi
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.imeanttobe.app901.R
import com.imeanttobe.app901.data.enum.AnalysisStatus

@OptIn(ExperimentalMaterial3ExpressiveApi::class)
@Composable
fun AnalysisBottomButton(
    onClickCloseButton: () -> Unit,
    onClickConfirmButton: () -> Unit,
    onClickCompleteButton: () -> Unit,
    onClickShareButton: () -> Unit,
    state: AnalysisStatus,
    modifier: Modifier = Modifier,
) {
    Row(
        horizontalArrangement = Arrangement.spacedBy(8.dp),
        modifier = modifier,
    ) {
        val size = ButtonDefaults.MediumContainerHeight

        // Confirm
        Button(
            colors =
                ButtonDefaults.buttonColors().copy(
                    containerColor = MaterialTheme.colorScheme.secondaryContainer,
                    contentColor = MaterialTheme.colorScheme.onSecondaryContainer,
                ),
            onClick = { onClickCloseButton() },
            contentPadding = ButtonDefaults.contentPaddingFor(size),
            modifier = Modifier.weight(1f),
        ) {
            Text(
                text = stringResource(R.string.close),
                style = ButtonDefaults.textStyleFor(size),
            )
        }

        // Complete
        if (state == AnalysisStatus.IN_PROGRESS || state == AnalysisStatus.CONFIRMED) {
            Button(
                colors =
                    ButtonDefaults.buttonColors().copy(
                        containerColor = MaterialTheme.colorScheme.tertiaryContainer,
                        contentColor = MaterialTheme.colorScheme.onTertiaryContainer,
                    ),
                onClick = { if (state == AnalysisStatus.IN_PROGRESS) onClickConfirmButton() else onClickCompleteButton() },
                contentPadding = ButtonDefaults.contentPaddingFor(size),
                modifier = Modifier.weight(1f),
            ) {
                Text(
                    text =
                        if (state ==
                            AnalysisStatus.IN_PROGRESS
                        ) {
                            stringResource(R.string.confirm_cart)
                        } else {
                            stringResource(R.string.complete_cart)
                        },
                    style = ButtonDefaults.textStyleFor(size),
                    fontWeight = FontWeight.Bold,
                )
            }
        }

        // Share
        Button(
            colors =
                ButtonDefaults.buttonColors().copy(
                    containerColor = MaterialTheme.colorScheme.secondaryContainer,
                    contentColor = MaterialTheme.colorScheme.onSecondaryContainer,
                ),
            contentPadding = ButtonDefaults.contentPaddingFor(size),
            onClick = {
                onClickShareButton()
            },
        ) {
            Icon(
                imageVector = Icons.Rounded.Share,
                contentDescription = "Share",
                modifier = Modifier.size(ButtonDefaults.iconSizeFor(size)),
            )
        }
    }
}
