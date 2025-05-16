package com.imeanttobe.app901.ui.login

import android.widget.Toast
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.imePadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.rounded.Login
import androidx.compose.material.icons.rounded.Clear
import androidx.compose.material.icons.rounded.Visibility
import androidx.compose.material.icons.rounded.VisibilityOff
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3ExpressiveApi
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.LoadingIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.imeanttobe.app901.R
import com.imeanttobe.app901.data.type.ConcurrencyState
import com.imeanttobe.app901.navigation.NavItem

@OptIn(ExperimentalMaterial3ExpressiveApi::class)
@Composable
fun LoginPage(
    navigate: (String) -> Unit,
    viewModel: LoginPageViewModel = hiltViewModel(),
) {
    val context = LocalContext.current

    // If login is successful, navigate to home
    LaunchedEffect(key1 = viewModel.loginState.value) {
        if (viewModel.loginState.value is ConcurrencyState.Success) {
            navigate(NavItem.HomeNavItem.route)
        } else if (viewModel.loginState.value is ConcurrencyState.Failure) {
            Toast
                .makeText(
                    context,
                    context.getString(R.string.failed_to_login),
                    Toast.LENGTH_SHORT,
                ).show()
        }
    }

    Scaffold(
        modifier =
            Modifier.fillMaxSize(),
    ) { innerPadding ->
        Column(
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier =
                Modifier
                    .fillMaxSize()
                    .padding(innerPadding)
                    .imePadding(),
        ) {
            Card(
                modifier =
                    Modifier
                        .fillMaxWidth()
                        .padding(16.dp),
            ) {
                // Text fields
                Column(
                    verticalArrangement = Arrangement.spacedBy(16.dp),
                    modifier = Modifier.padding(16.dp),
                ) {
                    OutlinedTextField(
                        value = viewModel.email.value,
                        onValueChange = { newValue -> viewModel.setEmail(newValue) },
                        label = { Text(text = stringResource(id = R.string.email)) },
                        maxLines = 1,
                        singleLine = true,
                        placeholder = { Text(text = stringResource(id = R.string.example_email)) },
                        keyboardOptions = KeyboardOptions(imeAction = ImeAction.Next, keyboardType = KeyboardType.Email),
                        trailingIcon = {
                            IconButton(
                                onClick = { viewModel.setEmail("") },
                            ) {
                                Icon(
                                    imageVector = Icons.Rounded.Clear,
                                    contentDescription = "Clear",
                                )
                            }
                        },
                        modifier = Modifier.fillMaxWidth(),
                    )
                    OutlinedTextField(
                        value = viewModel.password.value,
                        onValueChange = { newValue -> viewModel.setPassword(newValue) },
                        label = { Text(text = stringResource(id = R.string.password)) },
                        maxLines = 1,
                        singleLine = true,
                        placeholder = { Text(text = stringResource(id = R.string.example_password)) },
                        keyboardOptions = KeyboardOptions(imeAction = ImeAction.Done, keyboardType = KeyboardType.Password),
                        visualTransformation =
                            if (viewModel.isPasswordVisible.value) {
                                VisualTransformation.None
                            } else {
                                PasswordVisualTransformation()
                            },
                        trailingIcon = {
                            Row(
                                verticalAlignment = Alignment.CenterVertically,
                            ) {
                                IconButton(
                                    onClick = { viewModel.setIsPasswordVisible(!viewModel.isPasswordVisible.value) },
                                ) {
                                    Icon(
                                        imageVector =
                                            if (viewModel.isPasswordVisible.value) {
                                                Icons.Rounded.VisibilityOff
                                            } else {
                                                Icons.Rounded.Visibility
                                            },
                                        contentDescription = "Set password visible",
                                    )
                                }
                                IconButton(
                                    onClick = { viewModel.setPassword("") },
                                ) {
                                    Icon(
                                        imageVector = Icons.Rounded.Clear,
                                        contentDescription = "Clear",
                                    )
                                }
                            }
                        },
                        modifier = Modifier.fillMaxWidth(),
                    )
                }
            }

            // Login button
            Button(
                onClick = { viewModel.login() },
                modifier =
                    Modifier
                        .padding(16.dp)
                        .fillMaxWidth()
                        .height(60.dp),
            ) {
                if (viewModel.loginState.value is ConcurrencyState.Loading) {
                    LoadingIndicator(color = MaterialTheme.colorScheme.onPrimaryContainer)
                } else {
                    Icon(
                        imageVector = Icons.AutoMirrored.Rounded.Login,
                        contentDescription = "Login",
                    )
                    Spacer(modifier = Modifier.width(8.dp))
                    Text(text = stringResource(id = R.string.login))
                }
            }

            // Register button
            TextButton(
                onClick = { navigate(NavItem.RegisterNavItem.route) },
            ) {
                Text(text = stringResource(id = R.string.dont_have_account))
            }
        }
    }
}

@Preview
@Composable
fun LoginPagePreview() {
    LoginPage(navigate = {})
}
