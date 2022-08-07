package com.ecommerce.service;

import com.ecommerce.entity.CartItem;
import com.ecommerce.entity.ShoppingCart;

public interface ShoppingCartService {

    ShoppingCart addShoppingCartFirstTime(String shoppingCartId, String sessionToken, int quantity);

    ShoppingCart addToExistingShoppingCart(String shoppingCartId, String sessionToken, int quantity);

    ShoppingCart getShoppingCartBySessionToken(String sessionToken);

    CartItem updateShoppingCartItem(String shoppingCartId, int quantity);

    ShoppingCart removeCartIemFromShoppingCart(String shoppingCartId, String sessionToken);

    void clearShoppingCart(String sessionToken);
}
