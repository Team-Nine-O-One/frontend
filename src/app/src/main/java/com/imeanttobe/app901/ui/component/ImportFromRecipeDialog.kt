package com.imeanttobe.app901.ui.component

import android.widget.Toast
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.AutoGraph
import androidx.compose.material.icons.rounded.Clear
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.ContainedLoadingIndicator
import androidx.compose.material3.ExperimentalMaterial3ExpressiveApi
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.LocalContentColor
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import com.imeanttobe.app901.R
import com.imeanttobe.app901.data.type.ConcurrencyState

@OptIn(ExperimentalMaterial3ExpressiveApi::class)
@Composable
fun ImportFromRecipeDialog(
    urlFieldContent: String,
    concurrencyState: ConcurrencyState,
    onUrlChange: (String) -> Unit,
    onDismiss: () -> Unit,
    onConfirm: () -> Unit,
) {
    val context = LocalContext.current

    LaunchedEffect(key1 = concurrencyState) {
        if (concurrencyState is ConcurrencyState.Success) {
            onDismiss()
        } else if (concurrencyState is ConcurrencyState.Failure) {
            onDismiss()
            Toast.makeText(context, concurrencyState.message, Toast.LENGTH_SHORT).show()
        }
    }

    AlertDialog(
        icon = {
            Icon(
                imageVector = Icons.Rounded.AutoGraph,
                contentDescription = "Import items from recipe",
            )
        },
        onDismissRequest = { if (concurrencyState == ConcurrencyState.Default) onDismiss() else Unit },
        title = {
            Text(
                text = stringResource(R.string.import_from_recipe),
            )
        },
        text = {
            if (concurrencyState == ConcurrencyState.Default) {
                Column {
                    Text(text = stringResource(R.string.tips_import_from_recipe))
                    Spacer(modifier = Modifier.height(16.dp))
                    OutlinedTextField(
                        value = urlFieldContent,
                        onValueChange = { newValue -> onUrlChange(newValue.replace("\n", "")) },
                        maxLines = 1,
                        singleLine = true,
                        placeholder = {
                            Text(
                                text = stringResource(id = R.string.example_import_from_recipe),
                                color = LocalContentColor.current.copy(alpha = 0.7f),
                            )
                        },
                        keyboardOptions = KeyboardOptions(imeAction = ImeAction.Done, keyboardType = KeyboardType.Uri),
                        trailingIcon = {
                            IconButton(
                                onClick = { onUrlChange("") },
                            ) {
                                Icon(
                                    imageVector = Icons.Rounded.Clear,
                                    contentDescription = "Clear",
                                )
                            }
                        },
                        modifier = Modifier.fillMaxWidth(),
                    )
                }
            } else {
                Box(
                    contentAlignment = Alignment.Center,
                    modifier = Modifier.fillMaxWidth().padding(24.dp),
                ) {
                    ContainedLoadingIndicator()
                }
            }
        },
        confirmButton = {
            if (concurrencyState == ConcurrencyState.Default) {
                TextButton(onClick = {
                    onConfirm()
                    onUrlChange("")
                }) {
                    Text(text = stringResource(R.string.importing))
                }
            }
        },
        dismissButton = {
            if (concurrencyState == ConcurrencyState.Default) {
                TextButton(onClick = {
                    onUrlChange("")
                    onDismiss()
                }) {
                    Text(text = stringResource(R.string.close))
                }
            }
        },
    )
}
