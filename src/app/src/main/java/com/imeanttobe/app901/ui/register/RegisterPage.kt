package com.imeanttobe.app901.ui.register

import android.widget.Toast
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.imePadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.rounded.ArrowBack
import androidx.compose.material.icons.rounded.Clear
import androidx.compose.material.icons.rounded.Email
import androidx.compose.material.icons.rounded.Password
import androidx.compose.material.icons.rounded.Person
import androidx.compose.material.icons.rounded.PersonAddAlt
import androidx.compose.material.icons.rounded.Visibility
import androidx.compose.material.icons.rounded.VisibilityOff
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExperimentalMaterial3ExpressiveApi
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.LoadingIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
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
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.imeanttobe.app901.R
import com.imeanttobe.app901.data.type.ConcurrencyState
import com.imeanttobe.app901.navigation.NavItem
import com.imeanttobe.app901.ui.register.component.TextFieldTips

@OptIn(
    ExperimentalMaterial3Api::class,
    ExperimentalMaterial3ExpressiveApi::class,
    ExperimentalLayoutApi::class,
)
@Composable
fun RegisterPage(
    navigate: (String) -> Unit,
    viewModel: RegisterPageViewModel = hiltViewModel(),
) {
    val scrollState = rememberScrollState()
    val context = LocalContext.current

    // If login is successful, navigate to home
    LaunchedEffect(key1 = viewModel.registerState.value) {
        if (viewModel.registerState.value is ConcurrencyState.Success) {
            navigate(NavItem.HomeNavItem.route)
        } else if (viewModel.registerState.value is ConcurrencyState.Failure) {
            Toast
                .makeText(
                    context,
                    context.getString(R.string.failed_to_login),
                    Toast.LENGTH_SHORT,
                ).show()
        }
    }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(text = stringResource(id = R.string.register)) },
                navigationIcon = {
                    IconButton(
                        onClick = { navigate(NavItem.LoginNavItem.route) },
                    ) {
                        Icon(
                            imageVector = Icons.AutoMirrored.Rounded.ArrowBack,
                            contentDescription = "Back",
                        )
                    }
                },
            )
        },
        modifier = Modifier.fillMaxSize(),
    ) { innerPadding ->
        Box(
            modifier =
                Modifier
                    .fillMaxSize()
                    .padding(innerPadding),
        ) {
            Column(
                verticalArrangement = Arrangement.spacedBy(8.dp),
                modifier =
                    Modifier
                        .fillMaxSize()
                        .verticalScroll(scrollState)
                        .imePadding()
                        .padding(horizontal = 16.dp),
            ) {
                // Email text field
                TextFieldTips(
                    title = stringResource(id = R.string.email),
                    icon = Icons.Rounded.Email,
                    content = stringResource(id = R.string.tips_email),
                )
                OutlinedTextField(
                    value = viewModel.email.value,
                    onValueChange = { newValue -> viewModel.setEmail(newValue) },
                    label = { Text(text = stringResource(id = R.string.email)) },
                    isError = viewModel.emailErrorMessage.value.isNotEmpty(),
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
                if (viewModel.emailErrorMessage.value.isNotEmpty()) {
                    Text(
                        text = viewModel.emailErrorMessage.value,
                        color = MaterialTheme.colorScheme.error,
                        style = MaterialTheme.typography.labelMedium,
                        modifier = Modifier.padding(horizontal = 4.dp),
                    )
                }
                Spacer(modifier = Modifier.height(16.dp))

                // Nickname text field
                TextFieldTips(
                    title = stringResource(id = R.string.nickname),
                    icon = Icons.Rounded.Person,
                    content = stringResource(id = R.string.tips_nickname),
                )
                OutlinedTextField(
                    value = viewModel.nickname.value,
                    onValueChange = { newValue -> viewModel.setNickname(newValue) },
                    label = { Text(text = stringResource(id = R.string.nickname)) },
                    isError = viewModel.nicknameErrorMessage.value.isNotEmpty(),
                    maxLines = 1,
                    singleLine = true,
                    placeholder = { Text(text = stringResource(id = R.string.example_nickname)) },
                    keyboardOptions = KeyboardOptions(imeAction = ImeAction.Next),
                    trailingIcon = {
                        IconButton(
                            onClick = { viewModel.setNickname("") },
                        ) {
                            Icon(
                                imageVector = Icons.Rounded.Clear,
                                contentDescription = "Clear",
                            )
                        }
                    },
                    modifier = Modifier.fillMaxWidth(),
                )
                if (viewModel.nicknameErrorMessage.value.isNotEmpty()) {
                    Text(
                        text = viewModel.nicknameErrorMessage.value,
                        color = MaterialTheme.colorScheme.error,
                        style = MaterialTheme.typography.labelMedium,
                        modifier = Modifier.padding(horizontal = 4.dp),
                    )
                }
                Spacer(modifier = Modifier.height(16.dp))

                // Password text field
                TextFieldTips(
                    title = stringResource(id = R.string.password),
                    icon = Icons.Rounded.Password,
                    content = stringResource(id = R.string.tips_password),
                )
                OutlinedTextField(
                    value = viewModel.password.value,
                    onValueChange = { newValue -> viewModel.setPassword(newValue) },
                    label = { Text(text = stringResource(id = R.string.password)) },
                    isError = viewModel.passwordErrorMessage.value.isNotEmpty(),
                    maxLines = 1,
                    singleLine = true,
                    placeholder = { Text(text = stringResource(id = R.string.example_password)) },
                    keyboardOptions = KeyboardOptions(imeAction = ImeAction.Next, keyboardType = KeyboardType.Password),
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
                if (viewModel.passwordErrorMessage.value.isNotEmpty()) {
                    Text(
                        text = viewModel.passwordErrorMessage.value,
                        color = MaterialTheme.colorScheme.error,
                        style = MaterialTheme.typography.labelMedium,
                        modifier = Modifier.padding(horizontal = 4.dp),
                    )
                }
                Spacer(modifier = Modifier.height(16.dp))

                // Confirm password text field
                TextFieldTips(
                    title = stringResource(id = R.string.confirm_password),
                    icon = Icons.Rounded.Password,
                    content = stringResource(id = R.string.tips_confirm_password),
                )
                OutlinedTextField(
                    value = viewModel.confirmPassword.value,
                    onValueChange = { newValue -> viewModel.setConfirmPassword(newValue) },
                    label = { Text(text = stringResource(id = R.string.confirm_password)) },
                    isError = viewModel.confirmPasswordErrorMessage.value.isNotEmpty(),
                    maxLines = 1,
                    singleLine = true,
                    placeholder = { Text(text = stringResource(id = R.string.example_password)) },
                    keyboardOptions = KeyboardOptions(imeAction = ImeAction.Done, keyboardType = KeyboardType.Password),
                    visualTransformation =
                        if (viewModel.isConfirmPasswordVisible.value) {
                            VisualTransformation.None
                        } else {
                            PasswordVisualTransformation()
                        },
                    trailingIcon = {
                        Row(
                            verticalAlignment = Alignment.CenterVertically,
                        ) {
                            IconButton(
                                onClick = { viewModel.setIsConfirmPasswordVisible(!viewModel.isConfirmPasswordVisible.value) },
                            ) {
                                Icon(
                                    imageVector =
                                        if (viewModel.isConfirmPasswordVisible.value) {
                                            Icons.Rounded.VisibilityOff
                                        } else {
                                            Icons.Rounded.Visibility
                                        },
                                    contentDescription = "Set confirm password visible",
                                )
                            }
                            IconButton(
                                onClick = { viewModel.setConfirmPassword("") },
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
                if (viewModel.confirmPasswordErrorMessage.value.isNotEmpty()) {
                    Text(
                        text = viewModel.confirmPasswordErrorMessage.value,
                        color = MaterialTheme.colorScheme.error,
                        style = MaterialTheme.typography.labelMedium,
                        modifier = Modifier.padding(horizontal = 4.dp),
                    )
                }

                // Spacer for register button
                Spacer(modifier = Modifier.height(72.dp))
            }

            // Register button
            Button(
                onClick = { viewModel.register(context = context) },
                modifier =
                    Modifier
                        .padding(16.dp)
                        .fillMaxWidth()
                        .height(60.dp)
                        .align(Alignment.BottomCenter),
            ) {
                if (viewModel.registerState.value is ConcurrencyState.Loading) {
                    LoadingIndicator(color = MaterialTheme.colorScheme.onPrimary)
                } else {
                    Icon(
                        imageVector = Icons.Rounded.PersonAddAlt,
                        contentDescription = "Register",
                    )
                    Spacer(modifier = Modifier.width(8.dp))
                    Text(text = stringResource(id = R.string.register))
                }
            }
        }
    }
}
