package com.imeanttobe.app901.ui.analysis.component

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
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.vector.rememberVectorPainter
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil3.compose.AsyncImage
import com.imeanttobe.app901.R
import com.imeanttobe.app901.data.model.Product
import com.imeanttobe.app901.ui.component.EmphasizedText
import com.imeanttobe.app901.util.Formatter
import kotlin.math.ceil

@Composable
fun ProductListItem(
    product: Product,
    imageUrl: String,
    modifier: Modifier = Modifier,
) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(8.dp),
        modifier = modifier,
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
                    .background(color = MaterialTheme.colorScheme.surfaceContainerHighest)
                    .padding(4.dp),
        )

        // Details of product
        Column(
            modifier = Modifier.weight(1f),
        ) {
            Text(
                text = product.name,
                style = MaterialTheme.typography.titleMedium,
                color = MaterialTheme.colorScheme.onSurfaceVariant,
            )
            Text(
                text =
                    stringResource(
                        R.string.format_price_per_100_gram,
                        Formatter.formatPrice(ceil(product.pricePer100g).toInt()),
                    ),
                style = MaterialTheme.typography.bodySmall,
                color = MaterialTheme.colorScheme.onSurfaceVariant.copy(alpha = 0.7f),
            )
        }

        // Price
        EmphasizedText(
            content = product.price,
            tail = stringResource(R.string.won),
        )
    }
}

@Preview
@Composable
fun ProductListItemPreview() {
    ProductListItem(
        imageUrl = "",
        product = Product.getDefaultObject(),
    )
}
