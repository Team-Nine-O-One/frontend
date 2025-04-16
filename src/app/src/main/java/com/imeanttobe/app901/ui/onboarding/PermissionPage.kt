package com.imeanttobe.app901.ui.onboarding

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

@Composable
fun PermissionPage() {
    Scaffold(
        modifier = Modifier
    ) { innerPadding ->
        Surface(modifier = Modifier.padding(innerPadding)) {}
    }
}