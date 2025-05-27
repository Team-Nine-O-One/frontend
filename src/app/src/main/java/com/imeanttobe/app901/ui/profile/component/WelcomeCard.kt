package com.imeanttobe.app901.ui.profile.component

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedCard
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.imeanttobe.app901.R

@Composable
fun WelcomeCard(
    nickname: String,
    modifier: Modifier = Modifier,
) {
    OutlinedCard(
        border = CardDefaults.outlinedCardBorder().copy(width = 2.0.dp),
        modifier =
            Modifier
                .fillMaxWidth()
                .then(modifier),
    ) {
        Column(
            modifier = Modifier.padding(24.dp),
            verticalArrangement = Arrangement.spacedBy(8.dp),
        ) {
            Text(
                text = stringResource(R.string.format_nickname, nickname),
                style = MaterialTheme.typography.headlineSmall,
                fontWeight = FontWeight.Bold,
            )

            Text(
                text = stringResource(R.string.welcome),
                style = MaterialTheme.typography.headlineSmall,
            )
        }
    }
}
