package com.imeanttobe.app901.ui.history.component

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.ExperimentalMaterial3ExpressiveApi
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.imeanttobe.app901.R
import com.imeanttobe.app901.data.model.SimplifiedMart
import com.imeanttobe.app901.ui.component.EmphasizedText
import com.imeanttobe.app901.ui.component.MartLogo

@OptIn(ExperimentalMaterial3ExpressiveApi::class)
@Composable
fun MartInfoItem(
    contentColor: Color,
    priceColor: Color,
    mart: SimplifiedMart,
    modifier: Modifier = Modifier,
) {
    val itemsText =
        if (mart.productNames.isNotEmpty()) {
            stringResource(R.string.format_mart_items, mart.productNames.first(), mart.productNames.size)
        } else {
            stringResource(R.string.format_total_items_count, mart.productNames.size)
        }

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
            // Mart logo
            MartLogo(
                name = mart.martName,
                modifier = Modifier.size(48.dp).clip(CircleShape),
            )

            // Title
            Column(verticalArrangement = Arrangement.spacedBy(0.dp)) {
                Text(
                    text = mart.martName,
                    style = MaterialTheme.typography.titleMedium,
                    color = contentColor,
                )
                Text(
                    text = itemsText,
                    style = MaterialTheme.typography.bodySmall,
                    color = contentColor,
                )
            }
        }

        // Price
        EmphasizedText(
            content = mart.totalPrice,
            color = priceColor,
            tail = stringResource(R.string.won),
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
                productNames = listOf("Product 1", "Product 2"),
                totalPrice = 10000,
            ),
        contentColor = Color.White,
        priceColor = Color.Black,
    )
}

@Preview
@Composable
fun EMartInfoItemPreview() {
    MartInfoItem(
        mart =
            SimplifiedMart(
                martName = "이마트",
                productNames = listOf("Product 1", "Product 2"),
                totalPrice = 10000,
            ),
        contentColor = Color.White,
        priceColor = Color.Black,
    )
}
