package com.imeanttobe.app901.ui.home.component

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Clear
import androidx.compose.material.icons.rounded.PostAdd
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.unit.dp
import com.imeanttobe.app901.R

@Composable
fun CreatingMemoDialog(
    textFieldContent: String,
    onTextFieldChange: (String) -> Unit,
    onDismiss: () -> Unit,
    onConfirm: () -> Unit,
) {
    AlertDialog(
        icon = {
            Icon(
                imageVector = Icons.Rounded.PostAdd,
                contentDescription = "Create a new memo",
            )
        },
        onDismissRequest = { onDismiss() },
        title = {
            Text(
                text = stringResource(R.string.create_memo),
            )
        },
        text = {
            Column(verticalArrangement = Arrangement.spacedBy(4.dp)) {
                Text(
                    text = stringResource(R.string.tips_memo),
                )
                OutlinedTextField(
                    value = textFieldContent,
                    onValueChange = { newValue -> onTextFieldChange(newValue) },
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
        confirmButton = { onConfirm() },
        dismissButton = { onDismiss() },
    )
}
