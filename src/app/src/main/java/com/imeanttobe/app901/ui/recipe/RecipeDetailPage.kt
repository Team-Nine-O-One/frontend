package com.imeanttobe.app901.ui.recipe

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.rounded.ArrowBack
import androidx.compose.material.icons.rounded.AccessTime
import androidx.compose.material.icons.rounded.AddShoppingCart
import androidx.compose.material.icons.rounded.StackedLineChart
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.imeanttobe.app901.R
import com.imeanttobe.app901.data.model.Recipe

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun RecipeDetailPage(
    viewModel: RecipeDetailPageViewModel = hiltViewModel(),
    recipe: Recipe?,
    popBackStack: () -> Unit
) {
    val scrollState = rememberScrollState()
    val ingredients = listOf("마늘 5개", "올리브유 3T", "파슬리 3줄기", "페페론치노 3개")

    Scaffold(
        modifier = Modifier,
        topBar = {
            TopAppBar(
                title = {},
                navigationIcon = {
                    IconButton(onClick = { popBackStack() }) {
                        Icon(imageVector = Icons.AutoMirrored.Rounded.ArrowBack, contentDescription = null)
                    }
                }
            )
        }
    ) { innerPadding ->
        Surface(modifier = Modifier.padding(innerPadding)) {
            if (recipe != null) {
                Column(
                    modifier = Modifier.verticalScroll(scrollState),
                    verticalArrangement = Arrangement.spacedBy(4.dp)
                ) {
                    Image(
                        modifier = Modifier
                            .height(240.dp)
                            .clip(RoundedCornerShape(bottomStart = 12.dp, bottomEnd = 12.dp)),
                        painter = painterResource(R.drawable.aglioeolio),
                        contentDescription = null,
                        contentScale = ContentScale.FillWidth
                    )

                    Column(
                        modifier = Modifier.padding(16.dp),
                        verticalArrangement = Arrangement.spacedBy(4.dp)
                    ) {
                        Text(
                            text = recipe.name,
                            fontWeight = FontWeight.Bold,
                            style = MaterialTheme.typography.titleLarge
                        )
                        Row(
                            horizontalArrangement = Arrangement.spacedBy(8.dp),
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Row(
                                horizontalArrangement = Arrangement.spacedBy(4.dp),
                                verticalAlignment = Alignment.CenterVertically
                            ) {
                                Icon(imageVector = Icons.Rounded.StackedLineChart, contentDescription = null, modifier = Modifier.size(16.dp))
                                Text(text = "초급", style = MaterialTheme.typography.labelMedium)
                            }
                            Row(
                                horizontalArrangement = Arrangement.spacedBy(4.dp),
                                verticalAlignment = Alignment.CenterVertically
                            ) {
                                Icon(imageVector = Icons.Rounded.AccessTime, contentDescription = null, modifier = Modifier.size(16.dp))
                                Text(text = "20분 소요", style = MaterialTheme.typography.labelMedium)
                            }
                        }
                        HorizontalDivider(modifier = Modifier.padding(vertical = 16.dp))
                        
                        Text(
                            text = "재료",
                            fontWeight = FontWeight.Bold,
                            style = MaterialTheme.typography.titleMedium
                        )
                        ingredients.forEach { ingredient ->
                            Row(
                                horizontalArrangement = Arrangement.SpaceBetween,
                                verticalAlignment = Alignment.CenterVertically,
                                modifier = Modifier.fillMaxWidth()
                            ) { 
                                Text(text = ingredient)
                                IconButton(
                                    onClick = {}
                                ) {
                                    Icon(imageVector = Icons.Rounded.AddShoppingCart, contentDescription = null)
                                }
                            }
                        }
                        HorizontalDivider(modifier = Modifier.padding(vertical = 16.dp))

                        Text(
                            text = "레시피",
                            fontWeight = FontWeight.Bold,
                            style = MaterialTheme.typography.titleMedium
                        )
                        Text(
                            text = """
                                🍳 만드는 법
                                면 삶기
                                냄비에 물을 끓이고 소금을 충분히 넣은 뒤, 스파게티 면을 포장지에 적힌 시간보다 1분 덜 삶습니다. 면이 익는 동안 나머지 준비를 해주세요.
                                → 면수는 나중에 사용하므로 약간 덜어두세요.

                                마늘 준비
                                마늘은 얇게 슬라이스하거나 편으로 썰어주세요. 얇게 썰수록 풍미가 더 잘 배어납니다.

                                향유 만들기
                                팬에 올리브 오일을 두르고 약불에서 마늘과 페페론치노를 넣어 천천히 볶아줍니다.
                                마늘이 노릇하게 익을 때까지 약불에서 천천히 익혀야 쓴맛이 나지 않아요.

                                면과 섞기
                                익힌 면을 팬에 넣고, 덜어둔 면수도 함께 넣어 센 불로 볶듯이 섞어줍니다.
                                면수의 전분이 오일과 섞이면서 소스가 자연스럽게 만들어져요.

                                마무리
                                불을 끄고 기호에 따라 다진 파슬리를 넣어 향을 더합니다.
                                원한다면 파마산 치즈를 살짝 뿌려도 좋아요.

                                ✨ TIP
                                마늘은 절대 센 불에 볶지 마세요. 쉽게 타서 쓴맛이 날 수 있어요.

                                매콤한 맛을 원하면 페페론치노 양을 늘리고, 순한 맛을 원한다면 마늘만 사용해도 됩니다.
                            """.trimIndent(),
                            style = MaterialTheme.typography.bodyMedium
                        )
                        
                    }
                }
            } else {
                Text(text = "404")
            }
        }
    }
}