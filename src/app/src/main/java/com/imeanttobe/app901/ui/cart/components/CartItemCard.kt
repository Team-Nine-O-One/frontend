package com.imeanttobe.app901.ui.cart.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ElevatedCard
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import com.imeanttobe.app901.data.model.Cart

@Composable
fun CartItemCard(
    modifier: Modifier = Modifier,
    cart: Cart,
    onClick: () -> Unit,
) {
    val context = LocalContext.current

    ElevatedCard(
        modifier =
            Modifier
                .fillMaxWidth()
                .clip(CardDefaults.outlinedShape)
                .clickable { onClick() }
                .then(modifier),
    ) {
        /*
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Column(
                verticalArrangement = Arrangement.spacedBy(4.dp)
            ) {
                Text(text = cart, style = MaterialTheme.typography.titleMedium.copy(fontWeight = FontWeight.Bold))
                Row(
                    horizontalArrangement = Arrangement.spacedBy(4.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Icon(
                        modifier = Modifier.size(12.dp),
                        imageVector = Icons.Rounded.AccessTime,
                        contentDescription = null
                    )
                    Text(
                        text = Formatter.dateTimeToString(cart.updatedAt, context),
                        style = MaterialTheme.typography.labelMedium
                    ) // Modified date
                }
                Row(
                    horizontalArrangement = Arrangement.spacedBy(4.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Icon(
                        modifier = Modifier.size(12.dp),
                        imageVector = Icons.Rounded.LocationOn,
                        contentDescription = null
                    )
                    Text(
                        text = "이마트 용산점",
                        style = MaterialTheme.typography.labelMedium
                    ) // Mart name
                }
            }

            Column() {
                Text(
                    text = "상품 10건",
                    color = MaterialTheme.colorScheme.primary,
                    style = MaterialTheme.typography.labelSmall
                ) // Number of items
                Text(
                    text = "32,650",
                    color = MaterialTheme.colorScheme.primary,
                    style = MaterialTheme.typography.headlineMedium.copy(
                        fontWeight = FontWeight.Bold,
                        fontStyle = FontStyle.Italic
                    )
                ) // Total prices
            }
        }

         */
    }
}

@Preview
@Composable
fun CartItemCardPreview() {
    CartItemCard(cart = Cart.getDefaultInstance(), onClick = {})
}
