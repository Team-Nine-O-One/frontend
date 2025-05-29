package com.imeanttobe.app901.ui.profile

import android.widget.Toast
import androidx.activity.result.launch
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.core.view.isVisible
import androidx.hilt.navigation.compose.hiltViewModel
import com.imeanttobe.app901.R
import com.imeanttobe.app901.data.enum.ProfileSectionSheetState
import com.imeanttobe.app901.data.type.ConcurrencyState
import com.imeanttobe.app901.navigation.NavItem
import com.imeanttobe.app901.navigation.ProfileMenuItem
import com.imeanttobe.app901.ui.profile.component.ChangeNicknameBottomSheet
import com.imeanttobe.app901.ui.profile.component.ChangePasswordBottomSheet
import com.imeanttobe.app901.ui.profile.component.ProfileMenuListItem
import com.imeanttobe.app901.ui.profile.component.WelcomeCard
import kotlinx.coroutines.launch

// TODO: 바텀 시트 성공 시 자동으로 닫히게 개선
// TODO: 바텀 시트 실패 시 오류 메시지를 스낵 바로 출력하도록 개선

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ProfileSection(
    navigateAndClearBackStack: (String) -> Unit,
    viewModel: ProfileSectionViewModel = hiltViewModel(),
) {
    val scope = rememberCoroutineScope()
    val changeNicknameSheetState = rememberModalBottomSheetState()
    val changePasswordSheetState = rememberModalBottomSheetState()
    val nickname by viewModel.nickname.collectAsState()
    val changeNicknameState by viewModel.changeNicknameState.collectAsState()
    val changePasswordState by viewModel.changePasswordState.collectAsState()
    val context = LocalContext.current

    val menuItems =
        listOf(
            ProfileMenuItem.ChangeNicknameProfileMenuItem,
            ProfileMenuItem.ChangePasswordProfileMenuItem,
            ProfileMenuItem.LogoutProfileMenuItem,
        )
    val tintList =
        listOf(
            MaterialTheme.colorScheme.onPrimaryContainer,
            MaterialTheme.colorScheme.onSecondaryContainer,
            MaterialTheme.colorScheme.onTertiaryContainer,
        )
    val backgroundColorList =
        listOf(
            MaterialTheme.colorScheme.primaryContainer,
            MaterialTheme.colorScheme.secondaryContainer,
            MaterialTheme.colorScheme.tertiaryContainer,
        )

    LaunchedEffect(key1 = changeNicknameState) {
        when (changeNicknameState) {
            is ConcurrencyState.Success -> {
                scope
                    .launch {
                        changeNicknameSheetState.hide()
                    }.invokeOnCompletion {
                        if (!changeNicknameSheetState.isVisible) {
                            viewModel.setBottomSheetState(ProfileSectionSheetState.NONE)
                            viewModel.resetConcurrencyState() // Reset after sheet is hidden
                            viewModel.setNicknameTextfieldValue("")
                        }
                    }
            }
            is ConcurrencyState.Failure -> {
                scope
                    .launch {
                        changeNicknameSheetState.hide()
                    }.invokeOnCompletion {
                        if (!changeNicknameSheetState.isVisible) {
                            Toast
                                .makeText(
                                    context,
                                    context.getString(R.string.error_failed_to_change_nickname),
                                    Toast.LENGTH_LONG,
                                ).show()
                            viewModel.resetConcurrencyState() // Reset after toast is shown
                            viewModel.setNicknameTextfieldValue("")
                        }
                    }
            }
            else -> Unit
        }
    }

    LaunchedEffect(key1 = changePasswordState) {
        when (changePasswordState) {
            is ConcurrencyState.Success -> {
                scope
                    .launch {
                        changePasswordSheetState.hide()
                    }.invokeOnCompletion {
                        if (!changePasswordSheetState.isVisible) {
                            viewModel.setBottomSheetState(ProfileSectionSheetState.NONE)
                            viewModel.resetConcurrencyState() // Reset after sheet is hidden
                            viewModel.setPasswordTextfieldValue("")
                        }
                    }
            }
            is ConcurrencyState.Failure -> {
                scope
                    .launch {
                        changePasswordSheetState.hide()
                    }.invokeOnCompletion {
                        if (!changePasswordSheetState.isVisible) {
                            Toast
                                .makeText(
                                    context,
                                    context.getString(R.string.error_failed_to_change_password),
                                    Toast.LENGTH_LONG,
                                ).show()
                            viewModel.resetConcurrencyState() // Reset after toast is shown
                            viewModel.setPasswordTextfieldValue("")
                        }
                    }
            }
            else -> Unit
        }
    }

    Column(
        modifier = Modifier.fillMaxSize(),
    ) {
        WelcomeCard(
            nickname = nickname,
            modifier = Modifier.padding(16.dp),
        )

        menuItems.forEachIndexed { index, item ->
            ProfileMenuListItem(
                item = item,
                tint = tintList[index],
                backgroundColor = backgroundColorList[index],
                onClick = {
                    when (item) {
                        ProfileMenuItem.ChangeNicknameProfileMenuItem -> {
                            viewModel.setBottomSheetState(ProfileSectionSheetState.CHANGE_NICKNAME)
                        }
                        ProfileMenuItem.ChangePasswordProfileMenuItem -> {
                            viewModel.setBottomSheetState(ProfileSectionSheetState.CHANGE_PASSWORD)
                        }
                        ProfileMenuItem.LogoutProfileMenuItem -> {
                            viewModel.logout()
                            navigateAndClearBackStack(NavItem.LoginNavItem.route)
                        }
                    }
                },
                modifier = Modifier.padding(horizontal = 16.dp, vertical = 4.dp),
            )
        }
    }

    when (viewModel.bottomSheetState.value) {
        ProfileSectionSheetState.CHANGE_NICKNAME -> {
            ChangeNicknameBottomSheet(
                text = viewModel.nicknameTextfieldValue.value,
                onTextChange = { viewModel.setNicknameTextfieldValue(it) },
                onDismiss = {
                    scope
                        .launch {
                            changeNicknameSheetState.hide()
                        }.invokeOnCompletion {
                            if (!changeNicknameSheetState.isVisible) {
                                viewModel.setBottomSheetState(ProfileSectionSheetState.NONE)
                                viewModel.resetConcurrencyState()
                            }
                        }
                },
                onConfirm = { viewModel.updateNickname() },
                sheetState = changeNicknameSheetState,
                concurrenceState = changeNicknameState,
            )
        }
        ProfileSectionSheetState.CHANGE_PASSWORD -> {
            ChangePasswordBottomSheet(
                text = viewModel.passwordTextfieldValue.value,
                onTextChange = { viewModel.setPasswordTextfieldValue(it) },
                onDismiss = {
                    scope
                        .launch {
                            changePasswordSheetState.hide()
                        }.invokeOnCompletion {
                            if (!changePasswordSheetState.isVisible) {
                                viewModel.setBottomSheetState(ProfileSectionSheetState.NONE)
                                viewModel.resetConcurrencyState()
                            }
                        }
                },
                onConfirm = { viewModel.updatePassword() },
                sheetState = changePasswordSheetState,
                isPasswordVisible = viewModel.isPasswordVisible.value,
                setPassWordVisible = { viewModel.setIsPasswordVisible(it) },
                concurrenceState = changePasswordState,
            )
        }
        ProfileSectionSheetState.NONE -> {
            null
        }
    }
}
