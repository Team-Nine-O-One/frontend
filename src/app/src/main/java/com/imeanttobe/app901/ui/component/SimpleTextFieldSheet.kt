package com.imeanttobe.app901.ui.component

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Clear
import androidx.compose.material.icons.rounded.Visibility
import androidx.compose.material.icons.rounded.VisibilityOff
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ContainedLoadingIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExperimentalMaterial3ExpressiveApi
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.SheetState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import com.imeanttobe.app901.data.type.ConcurrencyState

@OptIn(ExperimentalMaterial3ExpressiveApi::class, ExperimentalMaterial3Api::class)
@Composable
fun SimpleTextFieldSheet(
    text: String,
    onTextChange: (String) -> Unit,
    onDismiss: () -> Unit,
    onConfirm: () -> Unit,
    content: String,
    title: String,
    buttonLabel: String,
    placeholder: String,
    sheetState: SheetState,
    concurrenceState: ConcurrencyState = ConcurrencyState.Default,
    isPasswordVisible: Boolean = false,
    setPassWordVisible: (Boolean) -> Unit = {},
    isPassword: Boolean = false,
) {
    val isLoadingIndicatorEnabled =
        (concurrenceState is ConcurrencyState.Loading) ||
            (concurrenceState is ConcurrencyState.Failure) ||
            (concurrenceState is ConcurrencyState.Success)

    ModalBottomSheet(
        onDismissRequest = onDismiss,
        sheetState = sheetState,
    ) {
        Column(
            modifier = Modifier.padding(horizontal = 24.dp, vertical = 8.dp),
            verticalArrangement = Arrangement.spacedBy(8.dp),
        ) {
            if (isLoadingIndicatorEnabled) {
                Box(
                    contentAlignment = Alignment.Center,
                    modifier = Modifier.fillMaxWidth().padding(vertical = 32.dp),
                ) {
                    ContainedLoadingIndicator()
                }
            } else {
                Text(
                    text = title,
                    style = MaterialTheme.typography.titleLargeEmphasized,
                    fontWeight = FontWeight.Bold,
                )

                Text(
                    text = content,
                    style = MaterialTheme.typography.bodyLarge,
                )

                OutlinedTextField(
                    value = text,
                    onValueChange = { newValue -> onTextChange(newValue) },
                    maxLines = 1,
                    singleLine = true,
                    placeholder = { Text(text = placeholder) },
                    keyboardOptions = KeyboardOptions(imeAction = ImeAction.Done),
                    trailingIcon = {
                        Row(
                            verticalAlignment = Alignment.CenterVertically,
                        ) {
                            if (isPassword) {
                                IconButton(
                                    onClick = { setPassWordVisible(!isPasswordVisible) },
                                ) {
                                    Icon(
                                        imageVector =
                                            if (isPasswordVisible) {
                                                Icons.Rounded.VisibilityOff
                                            } else {
                                                Icons.Rounded.Visibility
                                            },
                                        contentDescription = "Set password visible",
                                    )
                                }
                            }
                            IconButton(
                                onClick = { onTextChange("") },
                            ) {
                                Icon(
                                    imageVector = Icons.Rounded.Clear,
                                    contentDescription = "Clear",
                                )
                            }
                        }
                    },
                    visualTransformation =
                        if (isPassword && !isPasswordVisible) {
                            PasswordVisualTransformation()
                        } else {
                            VisualTransformation.None
                        },
                    modifier = Modifier.fillMaxWidth().padding(vertical = 24.dp),
                )

                Button(
                    onClick = { onConfirm() },
                    colors = ButtonDefaults.filledTonalButtonColors(),
                    modifier = Modifier.fillMaxWidth(),
                ) {
                    Text(text = buttonLabel)
                }
            }
        }
    }
}
