package com.imeanttobe.app901.ui.cart

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.rememberScrollState
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import com.imeanttobe.app901.R
import com.imeanttobe.app901.data.model.Cart

enum class MartRecommendOption(
    var label: Int,
) {
    DISTANCE(R.string.option_distance),
    BALANCED(R.string.option_balanced),
    PRICE(R.string.option_price),
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CartDetailPage(
    viewModel: CartDetailPageViewModel = hiltViewModel(),
    cart: Cart?,
    popBackStack: () -> Unit,
) {
    val scrollState = rememberScrollState()

    Box {}
    /*
    Scaffold(
        modifier = Modifier.fillMaxSize(),
        topBar = {
            TopAppBar(
                title = {
                    Text(text = "오류")
                },
                navigationIcon = {
                    IconButton(onClick = { popBackStack() }) {
                        Icon(imageVector = Icons.AutoMirrored.Rounded.ArrowBack, contentDescription = null)
                    }
                },
            )
        },
    ) { innerPadding ->
        Surface(modifier = Modifier.padding(innerPadding)) {
            if (cart != null) {
                Box {
                    // Cart detail part
                    Column(
                        modifier = Modifier.verticalScroll(scrollState),
                    ) {
                        /**
                         Text(
                         text = cart.name,
                         style = MaterialTheme.typography.headlineMedium.copy(fontWeight = FontWeight.Bold),
                         modifier = Modifier.padding(horizontal = 16.dp, vertical = 8.dp)
                         )
     */
                        Row(
                            modifier =
                                Modifier
                                    .fillMaxWidth()
                                    .padding(horizontal = 16.dp, vertical = 8.dp),
                            horizontalArrangement = Arrangement.SpaceBetween,
                            verticalAlignment = Alignment.CenterVertically,
                        ) {
                            // Checkbox area
                            Row(
                                verticalAlignment = Alignment.CenterVertically,
                                horizontalArrangement = Arrangement.spacedBy(4.dp),
                            ) {
                                Checkbox(
                                    checked = true,
                                    onCheckedChange = {},
                                )
                                Text(
                                    text = "전체 선택",
                                )
                            }

                            // Button area
                            Button(
                                onClick = {},
                            ) {
                                Text(
                                    text = "선택 삭제",
                                )
                            }
                        }
                        HorizontalDivider(modifier = Modifier.padding(horizontal = 16.dp))

                        // Items in cart
                        viewModel.ingredients.forEachIndexed { index, ingredient ->
                            val counter = remember { mutableIntStateOf(0) }
                            val enabled = remember { mutableStateOf(true) }

                            Column(
                                modifier = Modifier.padding(horizontal = 16.dp, vertical = 8.dp),
                                horizontalAlignment = Alignment.End,
                            ) {
                                IngredientItemCard(
                                    ingredient = ingredient,
                                    enabled = enabled.value,
                                    onCheckboxClick = { newValue -> enabled.value = newValue },
                                )
                                IngredientCounter(
                                    counter = counter.intValue,
                                    onIncrease = { counter.intValue += 1 },
                                    onDecrease = { if (counter.intValue > 0) counter.intValue -= 1 },
                                )
                            }

                            if (index != viewModel.ingredients.size - 1) {
                                HorizontalDivider(modifier = Modifier.padding(horizontal = 16.dp))
                            }
                        }

                        Box(modifier = Modifier.height(200.dp))
                    }

                    // Recommendation part
                    Box(modifier = Modifier.align(Alignment.BottomCenter)) {
                        RecommendedMartCard(
                            currentOption = viewModel.recommendOption,
                            onChangeOption = { newValue -> viewModel.setRecommendOption(newValue) },
                        )
                    }
                }
            } else {
                Text(text = "404")
            }
        }
    }

     */
}

/*
@Preview
@Composable
fun CartDetailPagePreview() {
    CartDetailPage(
        cart = Cart.getDefaultInstance(),
        popBackStack = {},
    )
}


 */
