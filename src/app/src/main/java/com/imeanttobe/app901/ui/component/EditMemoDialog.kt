package com.imeanttobe.app901.ui.component

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Clear
import androidx.compose.material.icons.rounded.EditNote
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.unit.dp
import com.imeanttobe.app901.R

@Composable
fun EditMemoDialog(
    textFieldContent: String,
    onTextFieldChange: (String) -> Unit,
    onDismiss: () -> Unit,
    onConfirm: () -> Unit,
) {
    AlertDialog(
        icon = {
            Icon(
                imageVector = Icons.Rounded.EditNote,
                contentDescription = "Edit a existing memo",
            )
        },
        onDismissRequest = { onDismiss() },
        title = { Text(text = stringResource(R.string.edit_memo)) },
        text = {
            Column(verticalArrangement = Arrangement.spacedBy(16.dp)) {
                Text(
                    text = stringResource(R.string.tips_edit_memo),
                )
                OutlinedTextField(
                    value = textFieldContent,
                    onValueChange = { newValue -> onTextFieldChange(newValue.replace("\n", "")) },
                    maxLines = 1,
                    singleLine = true,
                    placeholder = { Text(text = stringResource(id = R.string.example_memo)) },
                    keyboardOptions = KeyboardOptions(imeAction = ImeAction.Done),
                    trailingIcon = {
                        IconButton(
                            onClick = { onTextFieldChange("") },
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
            TextButton(onClick = {
                onConfirm()
                onTextFieldChange("")
                onDismiss()
            }) {
                Text(text = stringResource(R.string.edit))
            }
        },
        dismissButton = {
            TextButton(onClick = {
                onTextFieldChange("")
                onDismiss()
            }) {
                Text(text = stringResource(R.string.close))
            }
        },
    )
}
