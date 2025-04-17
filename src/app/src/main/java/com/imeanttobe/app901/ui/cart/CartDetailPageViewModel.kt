package com.imeanttobe.app901.ui.cart

import androidx.lifecycle.ViewModel
import com.imeanttobe.app901.type.Ingredient
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

    // Getter
    val ingredients: List<Ingredient>
        get() = _ingredients

    // Setter
}