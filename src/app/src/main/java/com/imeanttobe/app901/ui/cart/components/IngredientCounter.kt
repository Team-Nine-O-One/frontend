package com.imeanttobe.app901.ui.cart.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Add
import androidx.compose.material.icons.rounded.Remove
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Composable
fun IngredientCounter(
    counter: Int,
    onIncrease: () -> Unit,
    onDecrease: () -> Unit
) {
    Row(
        horizontalArrangement = Arrangement.spacedBy(4.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        IconButton(
            onClick = { onDecrease() }
        ) {
            Icon(
                imageVector = Icons.Rounded.Remove,
                contentDescription = "Decrease"
            )
        }

        Box(
            modifier = Modifier
                .clip(RoundedCornerShape(100.dp))
                .background(color = MaterialTheme.colorScheme.secondaryContainer)
                .widthIn(min = 32.dp)
                .padding(4.dp),
            contentAlignment = Alignment.Center
        ) {
            Text(
                text = counter.toString(),
                fontWeight = FontWeight.Bold,
                style = MaterialTheme.typography.labelLarge
            )
        }

        IconButton(
            onClick = { onIncrease() }
        ) {
            Icon(
                imageVector = Icons.Rounded.Add,
                contentDescription = "Increase"
            )
        }
    }
}

@Preview
@Composable
fun IngredientCounterPreview() {
    IngredientCounter(
        counter = 1,
        onIncrease = {},
        onDecrease = {}
    )
}

@Preview
@Composable
fun IngredientCounterLargeNumberPreview() {
    IngredientCounter(
        counter = 68,
        onIncrease = {},
        onDecrease = {}
    )
}