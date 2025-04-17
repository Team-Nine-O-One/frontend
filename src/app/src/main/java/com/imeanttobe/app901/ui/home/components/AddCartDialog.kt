package com.imeanttobe.app901.ui.home.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Add
import androidx.compose.material.icons.rounded.AddShoppingCart
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.imeanttobe.app901.R

@Composable
fun AddCartDialog(
    onDismissRequest: () -> Unit
) {
    val name = mutableStateOf("")
    val description = mutableStateOf("")

    AlertDialog(
        onDismissRequest = { onDismissRequest() },
        confirmButton = {
            TextButton(
                onClick = {
                    // TODO: Do something when confirm button is clicked
                    onDismissRequest()
                }
            ) {
                Icon(
                    imageVector = Icons.Rounded.Add,
                    contentDescription = "Add cart"
                )
                Text(
                    text = stringResource(R.string.add)
                )
            }
        },
        title = {
            Text(text = stringResource(R.string.add_new_cart), fontWeight = FontWeight.Bold)
        },
        text = {
            Column(
                verticalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                Text(text = stringResource(R.string.enter_cart_data))
                OutlinedTextField(
                    label = { Text(text = stringResource(R.string.name)) },
                    placeholder = { Text(text = stringResource(R.string.example_cart_name)) },
                    value = name.value,
                    onValueChange = { newValue -> name.value = newValue},
                    maxLines = 1,
                    singleLine = true,
                )
                OutlinedTextField(
                    label = { Text(text = stringResource(R.string.description)) },
                    placeholder = { Text(text = stringResource(R.string.example_cart_description)) },
                    value = description.value,
                    onValueChange = { newValue -> description.value = newValue},
                    maxLines = 1,
                    singleLine = true
                )
            }
        },
        icon = {
            Icon(
                imageVector = Icons.Rounded.AddShoppingCart,
                contentDescription = "Add cart"
            )
        }
    )
}

@Preview
@Composable
fun AddCartDialogPreview() {
    AddCartDialog(onDismissRequest = {})
}