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
import androidx.compose.ui.unit.dp
import com.imeanttobe.app901.R

@OptIn(ExperimentalMaterial3ExpressiveApi::class)
@Composable
fun AnalysisBottomButton(
    onClickCloseButton: () -> Unit,
    onClickCompleteButton: () -> Unit,
    onClickShareButton: () -> Unit,
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
                    containerColor = MaterialTheme.colorScheme.secondary,
                    contentColor = MaterialTheme.colorScheme.onSecondary,
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
        Button(
            onClick = { onClickCompleteButton() },
            contentPadding = ButtonDefaults.contentPaddingFor(size),
            modifier = Modifier.weight(1f),
        ) {
            Text(
                text = stringResource(R.string.complete_cart),
                style = ButtonDefaults.textStyleFor(size),
            )
        }

        // Share
        Button(
            colors =
                ButtonDefaults.buttonColors().copy(
                    containerColor = MaterialTheme.colorScheme.secondary,
                    contentColor = MaterialTheme.colorScheme.onSecondary,
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
