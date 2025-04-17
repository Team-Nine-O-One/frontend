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
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Composable
fun AddCartDialog() {
    AlertDialog(
        onDismissRequest = {},
        confirmButton = {
            TextButton(
                onClick = {}
            ) {
                Icon(
                    imageVector = Icons.Rounded.Add,
                    contentDescription = "Add cart"
                )
                Text(
                    text = "추가"
                )
            }
        },
        title = {
            Text(text = "새 장바구니 추가", fontWeight = FontWeight.Bold)
        },
        text = {
            Column(
                verticalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                Text(text = "새로 추가할 장비구니의 정보를 입력해주세요.")
                OutlinedTextField(
                    label = { Text(text = "이름") },
                    value = "",
                    onValueChange = {}
                )
                OutlinedTextField(
                    label = { Text(text = "설명") },
                    value = "",
                    onValueChange = {}
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
    AddCartDialog()
}