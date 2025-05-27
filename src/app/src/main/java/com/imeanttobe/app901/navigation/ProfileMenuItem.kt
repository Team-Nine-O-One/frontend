package com.imeanttobe.app901.navigation

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.rounded.Logout
import androidx.compose.material.icons.rounded.Edit
import androidx.compose.material.icons.rounded.Password
import androidx.compose.ui.graphics.vector.ImageVector
import com.imeanttobe.app901.R

sealed class ProfileMenuItem(
    val id: Int,
    val stringResId: Int,
    val label: String,
    val icon: ImageVector,
) {
    object ChangeNicknameProfileMenuItem : ProfileMenuItem(
        id = 0,
        stringResId = R.string.change_nickname,
        label = "change_nickname",
        icon = Icons.Rounded.Edit,
    )

    object ChangePasswordProfileMenuItem : ProfileMenuItem(
        id = 1,
        stringResId = R.string.change_password,
        label = "change_password",
        icon = Icons.Rounded.Password,
    )

    object LogoutProfileMenuItem : ProfileMenuItem(
        id = 2,
        stringResId = R.string.logout,
        label = "logout",
        icon = Icons.AutoMirrored.Rounded.Logout,
    )
}
