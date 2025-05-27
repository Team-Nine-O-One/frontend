package com.imeanttobe.app901.ui.profile

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.imeanttobe.app901.data.enum.ProfileSectionSheetState
import com.imeanttobe.app901.navigation.NavItem
import com.imeanttobe.app901.navigation.ProfileMenuItem
import com.imeanttobe.app901.ui.profile.component.ChangeNicknameBottomSheet
import com.imeanttobe.app901.ui.profile.component.ChangePasswordBottomSheet
import com.imeanttobe.app901.ui.profile.component.ProfileMenuListItem
import com.imeanttobe.app901.ui.profile.component.WelcomeCard

// TODO: 닉네임 바꾸면 바로 새로고침되도록 개선
// TODO: 디바이더 색상 더 자연스럽게 변경
// TODO: 바텀 시트 성공 시 자동으로 닫히게 개선
// TODO: 바텀 시트 실패 시 오류 메시지를 스낵 바로 출력하도록 개선

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ProfileSection(
    navigate: (String) -> Unit,
    viewModel: ProfileSectionViewModel = hiltViewModel(),
) {
    val changeNicknameSheetState = rememberModalBottomSheetState()
    val changePasswordSheetState = rememberModalBottomSheetState()

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

    Column(
        modifier = Modifier.fillMaxSize(),
    ) {
        WelcomeCard(
            nickname = viewModel.nickname.value,
            modifier = Modifier.padding(16.dp),
        )

        menuItems.forEachIndexed { index, item ->
            ProfileMenuListItem(
                item = item,
                isLastItem = index == menuItems.lastIndex,
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
                            navigate(NavItem.LoginNavItem.route)
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
                onDismiss = { viewModel.setBottomSheetState(ProfileSectionSheetState.NONE) },
                onConfirm = { viewModel.updateNickname() },
                sheetState = changeNicknameSheetState,
                concurrenceState = viewModel.concurrencyState.value,
            )
        }
        ProfileSectionSheetState.CHANGE_PASSWORD -> {
            ChangePasswordBottomSheet(
                text = viewModel.passwordTextfieldValue.value,
                onTextChange = { viewModel.setPasswordTextfieldValue(it) },
                onDismiss = { viewModel.setBottomSheetState(ProfileSectionSheetState.NONE) },
                onConfirm = { viewModel.updatePassword() },
                sheetState = changePasswordSheetState,
                isPasswordVisible = viewModel.isPasswordVisible.value,
                setPassWordVisible = { viewModel.setIsPasswordVisible(it) },
                concurrenceState = viewModel.concurrencyState.value,
            )
        }
        ProfileSectionSheetState.NONE -> {
            null
        }
    }
}
