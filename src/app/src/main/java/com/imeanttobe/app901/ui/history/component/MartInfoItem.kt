package com.imeanttobe.app901.ui.history.component

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Error
import androidx.compose.material.icons.rounded.Image
import androidx.compose.material3.ExperimentalMaterial3ExpressiveApi
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.rememberVectorPainter
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil3.compose.AsyncImage
import com.imeanttobe.app901.data.model.SimplifiedMart
import com.imeanttobe.app901.ui.component.PriceText

@OptIn(ExperimentalMaterial3ExpressiveApi::class)
@Composable
fun MartInfoItem(
    contentColor: Color,
    priceColor: Color,
    mart: SimplifiedMart,
    imageUrl: String,
    modifier: Modifier = Modifier,
) {
    Row(
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically,
        modifier = modifier,
    ) {
        // Title area
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(8.dp),
        ) {
            // Image
            AsyncImage(
                model = imageUrl,
                contentDescription = "Mart image",
                placeholder = rememberVectorPainter(image = Icons.Rounded.Image),
                error = rememberVectorPainter(image = Icons.Rounded.Error),
                modifier =
                    Modifier
                        .size(48.dp)
                        .clip(CircleShape)
                        .background(color = MaterialTheme.colorScheme.surfaceVariant)
                        .padding(4.dp),
            )

            // Title
            Column(verticalArrangement = Arrangement.spacedBy(0.dp)) {
                Text(
                    text = mart.martName,
                    style = MaterialTheme.typography.titleMedium,
                    fontWeight = FontWeight.Bold,
                    color = contentColor,
                )
                Text(
                    text = mart.displayName,
                    style = MaterialTheme.typography.bodyMedium,
                    color = contentColor,
                )
            }
        }

        // Price
        PriceText(
            price = mart.totalPrice,
            color = priceColor,
        )
    }
}

@Preview
@Composable
fun MartInfoItemPreview() {
    MartInfoItem(
        mart =
            SimplifiedMart(
                martName = "Mart Name",
                displayName = "Display Name",
                totalPrice = 10000,
            ),
        imageUrl = "https://picsum.photos/200",
        contentColor = Color.White,
        priceColor = Color.Black,
    )
}
