package com.imeanttobe.app901.ui.cart

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.absoluteOffset
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.Checkbox
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.imeanttobe.app901.type.Cart
import com.imeanttobe.app901.ui.cart.components.IngredientCounter
import com.imeanttobe.app901.ui.cart.components.IngredientItemCard
import com.imeanttobe.app901.ui.cart.components.RecommendOptionButton
import com.imeanttobe.app901.ui.cart.components.RecommendedMartCard

@Composable
fun CartDetailPage(
    modifier: Modifier = Modifier,
    viewModel: CartDetailPageViewModel = hiltViewModel(),
    cart: Cart?
) {
    val scrollState = rememberScrollState()

    Surface(
        modifier = modifier
    ) {
        if (cart != null) {
            Box() {
                // Cart detail part
                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(top = 8.dp)
                        .verticalScroll(scrollState)
                ) {
                    Text(
                        text = cart.name,
                        style = MaterialTheme.typography.headlineMedium.copy(fontWeight = FontWeight.Bold),
                        modifier = Modifier.padding(horizontal = 16.dp, vertical = 8.dp)
                    )
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(horizontal = 16.dp, vertical = 8.dp),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        // Checkbox area
                        Row(
                            verticalAlignment = Alignment.CenterVertically,
                            horizontalArrangement = Arrangement.spacedBy(4.dp)
                        ) {
                            Checkbox(
                                checked = true,
                                onCheckedChange = {}
                            )
                            Text(
                                text = "전체 선택"
                            )
                        }

                        // Button area
                        Button(
                            onClick = {}
                        ) {
                            Text(
                                text = "선택 삭제"
                            )
                        }
                    }
                    HorizontalDivider(modifier = Modifier.padding(horizontal = 16.dp))

                    // Items in cart
                    viewModel.ingredients.forEach { ingredient ->
                        Column(
                            modifier = Modifier.padding(horizontal = 16.dp, vertical = 8.dp),
                            verticalArrangement = Arrangement.spacedBy(4.dp),
                            horizontalAlignment = Alignment.End
                        ) {
                            IngredientItemCard(
                                ingredient = ingredient,
                                onClick = {}
                            )
                            IngredientCounter(
                                counter = 1,
                                onIncrease = { },
                                onDecrease = { }
                            )
                        }
                    }
                }

                // Recommendation part
                Column(
                    modifier = Modifier
                        .align(Alignment.BottomCenter)
                        .padding(16.dp),
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    RecommendOptionButton(
                        currentIndex = 0,
                        onChangeIndex = {}
                    )
                    RecommendedMartCard()
                }
            }
        } else {
            Text(text = "404")
        }

    }
}

@Preview
@Composable
fun CartDetailPagePreview() {
    CartDetailPage(
        cart = Cart.getDefaultInstance()
    )
}