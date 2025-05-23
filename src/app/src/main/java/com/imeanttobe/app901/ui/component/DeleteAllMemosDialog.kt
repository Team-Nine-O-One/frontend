package com.imeanttobe.app901.ui.component

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Delete
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import com.imeanttobe.app901.R

@Composable
fun DeleteAllMemosDialog(
    onDismiss: () -> Unit,
    onConfirm: () -> Unit,
) {
    AlertDialog(
        icon = {
            Icon(
                imageVector = Icons.Rounded.Delete,
                contentDescription = "Delete all memos",
            )
        },
        onDismissRequest = { onDismiss() },
        title = { Text(text = stringResource(R.string.delete_all_memos)) },
        text = { Text(text = stringResource(R.string.tips_delete_all_memos)) },
        confirmButton = {
            TextButton(onClick = {
                onConfirm()
                onDismiss()
            }) {
                Text(text = stringResource(R.string.delete))
            }
        },
        dismissButton = {
            TextButton(onClick = { onDismiss() }) {
                Text(text = stringResource(R.string.close))
            }
        },
    )
}
