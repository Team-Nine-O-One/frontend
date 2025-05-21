package com.imeanttobe.app901.ui.splash

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import com.imeanttobe.app901.navigation.NavItem
import kotlinx.coroutines.delay

@Composable
fun SplashPage(
    navigate: (String) -> Unit,
    viewModel: SplashPageViewModel = hiltViewModel(),
) {
    LaunchedEffect(Unit) {
        delay(500)
        if (viewModel.isLoggedIn()) {
            navigate(NavItem.HomeNavItem.route)
        } else {
            navigate(NavItem.LoginNavItem.route)
        }
    }

    Scaffold(
        modifier = Modifier.fillMaxSize(),
    ) { innerPadding ->
        Box(
            contentAlignment = Alignment.Center,
            modifier =
                Modifier
                    .fillMaxSize()
                    .padding(innerPadding),
        ) {
            Text(text = "Splash")
        }
    }
}
