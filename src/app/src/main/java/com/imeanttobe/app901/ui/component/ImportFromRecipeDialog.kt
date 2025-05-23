package com.imeanttobe.app901.ui.component

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.selection.selectable
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.AutoGraph
import androidx.compose.material.icons.rounded.Clear
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.RadioButton
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.semantics.Role
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
    val context = LocalContext.current
    val radioOptions = listOf(context.getString(R.string.youtube_shorts), context.getString(R.string.naver_blog))
    val (selectedOption, onOptionSelected) = remember { mutableStateOf(radioOptions[0]) }

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
            Column {
                Text(text = stringResource(R.string.tips_import_from_recipe))
                Spacer(modifier = Modifier.height(16.dp))

                radioOptions.forEach { text ->
                    Row(
                        Modifier
                            .clip(RoundedCornerShape(100.dp))
                            .selectable(
                                selected = (text == selectedOption),
                                onClick = { onOptionSelected(text) },
                                role = Role.RadioButton,
                            ).padding(vertical = 8.dp, horizontal = 16.dp),
                        verticalAlignment = Alignment.CenterVertically,
                    ) {
                        RadioButton(
                            selected = (text == selectedOption),
                            onClick = null, // null recommended for accessibility with screen readers
                        )
                        Text(
                            text = text,
                            style = MaterialTheme.typography.bodyLarge,
                            modifier = Modifier.padding(start = 16.dp),
                        )
                        if (text != radioOptions.last()) {
                            Spacer(modifier = Modifier.height(4.dp))
                        }
                    }
                }

                Spacer(modifier = Modifier.height(16.dp))
                OutlinedTextField(
                    value = urlFieldContent,
                    onValueChange = { newValue -> onUrlChange(newValue.replace("\n", "")) },
                    maxLines = 1,
                    singleLine = true,
                    placeholder = { Text(text = stringResource(id = R.string.example_import_from_recipe)) },
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
            TextButton(onClick = {
                onConfirm()
                onUrlChange("")
                onDismiss()
            }) {
                Text(text = stringResource(R.string.importing))
            }
        },
        dismissButton = {
            TextButton(onClick = {
                onUrlChange("")
                onDismiss()
            }) {
                Text(text = stringResource(R.string.close))
            }
        },
    )
}
