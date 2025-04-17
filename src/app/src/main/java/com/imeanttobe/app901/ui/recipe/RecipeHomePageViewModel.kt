package com.imeanttobe.app901.ui.recipe

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.imeanttobe.app901.type.Recipe
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class RecipeHomePageViewModel @Inject constructor() : ViewModel() {
    // Variables
    private val _recentRecipes = mutableListOf<Recipe>(
        Recipe.getDefaultInstance(),
        Recipe.getDefaultInstance(),
        Recipe.getDefaultInstance(),
    )
    private val _recommendedRecipes = mutableListOf<Recipe>(
        Recipe.getDefaultInstance(),
        Recipe.getDefaultInstance(),
        Recipe.getDefaultInstance(),
    )
    private val _searchBarText = mutableStateOf("")

    // Getter
    val recentRecipes: List<Recipe>
        get() = _recentRecipes
    val recommendedRecipes: List<Recipe>
        get() = _recommendedRecipes
    val searchBarText: String
        get() = _searchBarText.value

    // Setter
    fun setSearchBarText(text: String) {
        _searchBarText.value = text
    }
}