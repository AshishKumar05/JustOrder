package com.example.justorder.ui.homefragments.cart

import com.example.justorder.roomdb.CartItem

interface CartItemClickLestener {
    fun update(cartItem: CartItem)
    fun delete(cartItem: CartItem)
}