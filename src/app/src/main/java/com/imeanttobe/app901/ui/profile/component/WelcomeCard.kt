package com.imeanttobe.app901.ui.profile.component

import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedCard
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.composed
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.imeanttobe.app901.R

fun Modifier.blinking(durationMillis: Int = 1000): Modifier =
    composed {
        val alpha =
            rememberInfiniteTransition(
                label = "BlinkingAlpha",
            ).animateFloat(
                initialValue = 0f,
                targetValue = 1f,
                animationSpec =
                    infiniteRepeatable(
                        animation = tween(durationMillis),
                        repeatMode = RepeatMode.Reverse,
                    ),
                label = "AlphaAnimation",
            )
        this.then(Modifier.graphicsLayer { this.alpha = alpha.value })
    }

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
                .height(160.dp)
                .then(modifier),
    ) {
        Column(
            modifier =
                Modifier
                    .padding(24.dp)
                    .weight(1f),
            verticalArrangement = Arrangement.Bottom,
        ) {
            if (nickname.isEmpty() || nickname.isBlank()) {
                Box(
                    modifier =
                        Modifier
                            .blinking()
                            .height(
                                with(LocalDensity.current) {
                                    MaterialTheme.typography.headlineSmall.fontSize
                                        .toDp()
                                },
                            ).width(100.dp)
                            .clip(RoundedCornerShape(100.dp))
                            .background(color = MaterialTheme.colorScheme.surfaceContainerHighest),
                ) { }
            } else {
                Text(
                    text = stringResource(R.string.format_nickname, nickname),
                    style = MaterialTheme.typography.headlineSmall,
                    fontWeight = FontWeight.Bold,
                )
            }

            Text(
                text = stringResource(R.string.welcome),
                style = MaterialTheme.typography.headlineSmall,
            )
        }
    }
}
