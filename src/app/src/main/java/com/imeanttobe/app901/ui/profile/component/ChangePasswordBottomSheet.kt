package com.imeanttobe.app901.ui.profile.component

import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.SheetState
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import com.imeanttobe.app901.R
import com.imeanttobe.app901.data.type.ConcurrencyState
import com.imeanttobe.app901.ui.component.SimpleTextFieldSheet

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ChangePasswordBottomSheet(
    text: String,
    onTextChange: (String) -> Unit,
    onDismiss: () -> Unit,
    onConfirm: () -> Unit,
    sheetState: SheetState,
    isPasswordVisible: Boolean,
    setPassWordVisible: (Boolean) -> Unit,
    concurrenceState: ConcurrencyState,
) {
    SimpleTextFieldSheet(
        text = text,
        onTextChange = onTextChange,
        onDismiss = onDismiss,
        onConfirm = onConfirm,
        content = stringResource(id = R.string.tips_change_password),
        title = stringResource(id = R.string.change_password),
        buttonLabel = stringResource(id = R.string.change),
        placeholder = stringResource(id = R.string.example_password),
        sheetState = sheetState,
        isPassword = true,
        isPasswordVisible = isPasswordVisible,
        setPassWordVisible = setPassWordVisible,
        concurrenceState = concurrenceState,
    )
}
