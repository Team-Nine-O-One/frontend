package com.imeanttobe.app901.ui.recipe

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.HorizontalDivider
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
import com.imeanttobe.app901.ui.recipe.components.DetailedRecipeCard
import com.imeanttobe.app901.ui.recipe.components.SlimSearchBox

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun RecipeHomePage(
    modifier: Modifier = Modifier,
    viewModel: RecipeHomePageViewModel = hiltViewModel(),
    navigateToRecipeDetail: (Int) -> Unit,
) {
    val scrollState = rememberScrollState()

    Surface(
        modifier = modifier,
    ) {
        Column(
            modifier =
                Modifier
                    .fillMaxSize()
                    .padding(top = 8.dp)
                    .verticalScroll(scrollState),
        ) {
            // Search bar
            Box(modifier = Modifier.padding(horizontal = 16.dp)) {
                SlimSearchBox(
                    text = viewModel.searchBarText,
                    onValueChange = { newValue -> viewModel.setSearchBarText(newValue) },
                    onSearch = {},
                )
            }

            // Recent recipes
            Text(
                text = stringResource(R.string.recent_recipe),
                style = MaterialTheme.typography.headlineMedium.copy(fontWeight = FontWeight.Bold),
                modifier = Modifier.padding(horizontal = 16.dp, vertical = 8.dp),
            )
            viewModel.recentRecipes.forEach { recipe ->
                Box(modifier = Modifier.padding(horizontal = 16.dp, vertical = 8.dp)) {
                    DetailedRecipeCard(
                        recipe = recipe,
                        onClick = { navigateToRecipeDetail(10) },
                    )
                }
            }
            HorizontalDivider(modifier = Modifier.padding(16.dp))

            // Recommended recipe
            Text(
                text = stringResource(R.string.recommended_recipe),
                style = MaterialTheme.typography.headlineMedium.copy(fontWeight = FontWeight.Bold),
                modifier = Modifier.padding(horizontal = 16.dp, vertical = 8.dp),
            )
            viewModel.recommendedRecipes.forEach { recipe ->
                Box(modifier = Modifier.padding(horizontal = 16.dp, vertical = 8.dp)) {
                    DetailedRecipeCard(
                        recipe = recipe,
                        onClick = { navigateToRecipeDetail(10) },
                    )
                }
            }
        }
    }
}
