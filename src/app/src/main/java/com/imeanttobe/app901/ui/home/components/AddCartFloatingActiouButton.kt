package com.imeanttobe.app901.ui.home.components

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Add
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable

@Composable
fun AddCartFloatingActionButton(
    onClick: () -> Unit
) {
    FloatingActionButton(
        onClick = { onClick() }
    ) {
        Icon(
            imageVector = Icons.Rounded.Add,
            contentDescription = "Add cart"
        )
    }
}