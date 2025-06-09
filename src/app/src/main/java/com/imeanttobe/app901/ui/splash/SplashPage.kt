package com.imeanttobe.app901.ui.splash

import androidx.compose.foundation.Image
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.imeanttobe.app901.R
import com.imeanttobe.app901.navigation.NavItem
import kotlinx.coroutines.delay

@Composable
fun SplashPage(
    navigate: (String) -> Unit,
    viewModel: SplashPageViewModel = hiltViewModel(),
) {
    val logoSize = 120.dp

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
            Image(
                painter =
                    painterResource(
                        id = if (isSystemInDarkTheme()) R.drawable.logo_white_alpha else R.drawable.logo_gradient_alpha,
                    ),
                contentDescription = "Logo",
                modifier = Modifier.size(logoSize),
            )
        }
    }
}
