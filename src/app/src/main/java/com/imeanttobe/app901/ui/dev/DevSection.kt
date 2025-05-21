package com.imeanttobe.app901.ui.dev

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel

@Composable
fun DevSection(viewModel: DevSectionViewModel = hiltViewModel()) {
    Scaffold(
        modifier = Modifier,
    ) { innerPadding ->
        Column(
            modifier = Modifier.padding(innerPadding),
            verticalArrangement = Arrangement.spacedBy(16.dp),
        ) {
            Text(
                text = "id = ${viewModel.id.value}",
            )

            Button(
                onClick = {
                    viewModel.assignId()
                },
            ) {
                Text(text = "assign id")
            }
        }
    }
}
