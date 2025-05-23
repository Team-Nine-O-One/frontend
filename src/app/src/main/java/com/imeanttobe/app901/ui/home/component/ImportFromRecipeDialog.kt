package com.imeanttobe.app901.ui.home.component

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.AutoGraph
import androidx.compose.material.icons.rounded.Clear
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import com.imeanttobe.app901.R

@Composable
fun ImportFromRecipeDialog(
    urlFieldContent: String,
    onUrlChange: (String) -> Unit,
    onDismiss: () -> Unit,
    onConfirm: () -> Unit,
) {
    AlertDialog(
        icon = {
            Icon(
                imageVector = Icons.Rounded.AutoGraph,
                contentDescription = "Import items from recipe",
            )
        },
        onDismissRequest = { onDismiss() },
        title = {
            Text(
                text = stringResource(R.string.import_from_recipe),
            )
        },
        text = {
            Column(verticalArrangement = Arrangement.spacedBy(4.dp)) {
                Text(
                    text = stringResource(R.string.tips_memo),
                )
                OutlinedTextField(
                    value = urlFieldContent,
                    onValueChange = { newValue -> onUrlChange(newValue) },
                    maxLines = 1,
                    singleLine = true,
                    placeholder = { Text(text = stringResource(id = R.string.example_memo)) },
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
        },
        confirmButton = {
            onConfirm()
            onDismiss()
        },
        dismissButton = { onDismiss() },
    )
}
