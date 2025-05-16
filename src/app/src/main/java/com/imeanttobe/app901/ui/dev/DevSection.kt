package com.imeanttobe.app901.ui.dev

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.imeanttobe.app901.data.type.ConcurrencyState

@Composable
fun DevSection(viewModel: DevSectionViewModel = hiltViewModel()) {
    val authState = viewModel.authState.collectAsState()
    val authStateString =
        when (authState.value) {
            ConcurrencyState.Loading -> "Loading"
            ConcurrencyState.Success -> "Success"
            ConcurrencyState.Default -> "Initial"
            else -> "Unknown"
        }

    Scaffold(
        modifier = Modifier,
    ) { innerPadding ->
        Column(
            modifier = Modifier.padding(innerPadding),
            verticalArrangement = Arrangement.spacedBy(16.dp),
        ) {
            Button(
                onClick = { viewModel.register() },
            ) {
                Text(text = "register")
            }

            Button(
                onClick = { viewModel.login() },
            ) {
                Text(text = "login")
            }

            Text(text = authStateString)

            Button(
                onClick = { viewModel.getAllMemos() },
            ) {
                Text(text = "get all memos")
            }

            viewModel.memos.value.forEach { memo ->
                Text(
                    modifier =
                        Modifier
                            .background(MaterialTheme.colorScheme.primary)
                            .padding(8.dp),
                    text = memo.toString(),
                )
            }
        }
    }
}
