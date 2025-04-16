package com.imeanttobe.app901.ui.dev

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

@Composable
fun DevPage() {
    Scaffold(
        modifier = Modifier
    ) { innerPadding ->
        Surface(modifier = Modifier.padding(innerPadding)) {
            Text(
                text = "Splash Page",
            )
        }
    }
}