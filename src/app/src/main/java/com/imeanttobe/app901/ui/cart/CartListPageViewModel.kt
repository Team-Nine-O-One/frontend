package com.imeanttobe.app901.ui.cart

import androidx.lifecycle.ViewModel
import com.imeanttobe.app901.type.data.Cart
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class CartListPageViewModel @Inject constructor() : ViewModel() {
    // Variables
    private val _cart = mutableListOf<Cart>(
        Cart.getDefaultInstance(),
        Cart.getDefaultInstance(),
        Cart.getDefaultInstance(),
    )

    // Getter
    val cart: List<Cart>
        get() = _cart

    // Setter

}