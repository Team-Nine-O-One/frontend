package com.imeanttobe.app901.ui.profile.component

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.LocalContentColor
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.imeanttobe.app901.navigation.ProfileMenuItem

@Composable
fun ProfileMenuItem(
    item: ProfileMenuItem,
    tint: Color? = null,
    isLastItem: Boolean = false,
) {
    Column(
        modifier = Modifier.fillMaxWidth(),
    ) {
        Row(
            horizontalArrangement = Arrangement.spacedBy(16.dp),
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.padding(16.dp),
        ) {
            Icon(
                imageVector = item.icon,
                contentDescription = item.label,
                tint = tint ?: LocalContentColor.current,
            )

            Text(
                text = stringResource(item.stringResId),
                style = MaterialTheme.typography.bodyMedium,
            )
        }
        if (isLastItem) {
            HorizontalDivider(
                modifier =
                    Modifier
                        .padding(horizontal = 16.dp)
                        .fillMaxWidth(),
            )
        }
    }
}

@Preview
@Composable
fun ProfileMenuItemPreview() {
    ProfileMenuItem(item = ProfileMenuItem.ChangePasswordProfileMenuItem)
}
