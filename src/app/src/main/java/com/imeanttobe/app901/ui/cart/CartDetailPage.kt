package com.imeanttobe.app901.ui.cart

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

@Composable
fun CartDetailPage(
    itemId: Int?
) {
    Scaffold(
        modifier = Modifier
    ) { innerPadding ->
        if (itemId != null) {
            Surface(modifier = Modifier.padding(innerPadding)) {}
        } else {
            Surface(modifier = Modifier.padding(innerPadding)) {}
        }
    }
}