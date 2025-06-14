package com.imeanttobe.app901.ui.splash

import android.Manifest
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
import com.google.accompanist.permissions.ExperimentalPermissionsApi
import com.google.accompanist.permissions.isGranted
import com.google.accompanist.permissions.rememberPermissionState
import com.imeanttobe.app901.R
import com.imeanttobe.app901.navigation.NavItem
import kotlinx.coroutines.delay

@OptIn(ExperimentalPermissionsApi::class)
@Composable
fun SplashPage(
    navigate: (String) -> Unit,
    viewModel: SplashPageViewModel = hiltViewModel(),
) {
    val logoSize = 120.dp
    val fineLocationPermissionState = rememberPermissionState(Manifest.permission.ACCESS_FINE_LOCATION)

    LaunchedEffect(Unit) {
        delay(500)
        if (!fineLocationPermissionState.status.isGranted) {
            navigate(NavItem.PermissionNavItem.route)
        } else if (!viewModel.isLoggedIn()) {
            navigate(NavItem.LoginNavItem.route)
        } else {
            navigate(NavItem.HomeNavItem.route)
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
