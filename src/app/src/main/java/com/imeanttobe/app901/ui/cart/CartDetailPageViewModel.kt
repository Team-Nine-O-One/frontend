package com.imeanttobe.app901.ui.cart

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.imeanttobe.app901.data.model.Ingredient
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class CartDetailPageViewModel @Inject constructor() : ViewModel() {
    // Variables
    private val _ingredients = mutableListOf<Ingredient>(
        Ingredient.getDefaultInstance(),
        Ingredient.getDefaultInstance(),
        Ingredient.getDefaultInstance(),
        Ingredient.getDefaultInstance(),
        Ingredient.getDefaultInstance(),
        Ingredient.getDefaultInstance(),
    )
    private val _recommendOption = mutableStateOf(MartRecommendOption.BALANCED)

    // Getter
    val ingredients: List<Ingredient>
        get() = _ingredients
    val recommendOption: MartRecommendOption
        get() = _recommendOption.value

    // Setter
    fun setRecommendOption(newValue: MartRecommendOption) {
        _recommendOption.value = newValue
    }
}