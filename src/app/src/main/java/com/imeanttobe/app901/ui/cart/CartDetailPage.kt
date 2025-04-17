package com.imeanttobe.app901.ui.cart

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.imeanttobe.app901.R
import com.imeanttobe.app901.type.Cart
import com.imeanttobe.app901.ui.cart.components.CartItemCard

@Composable
fun CartDetailPage(
    modifier: Modifier = Modifier,
    viewModel: CartDetailPageViewModel = hiltViewModel(),
    cart: Cart?
) {
    Surface(
        modifier = modifier
    ) {
        if (cart != null) {
            Text(
                text = cart.name,
                style = MaterialTheme.typography.headlineMedium.copy(fontWeight = FontWeight.Bold),
                modifier = Modifier.padding(horizontal = 16.dp, vertical = 8.dp)
            )
            viewModel.ingredients.forEach { ingredient ->
                Box(modifier = Modifier.padding(horizontal = 16.dp, vertical = 8.dp)) {

                }
            }
        } else {
            Text(text = "404")
        }

    }
}