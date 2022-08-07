package com.ecommerce.service.impl;

import com.ecommerce.entity.CartItem;
import com.ecommerce.entity.ShoppingCart;
import com.ecommerce.repository.CartItemRepository;
import com.ecommerce.repository.ShoppingCartRepository;
import com.ecommerce.service.ProductService;
import com.ecommerce.service.ProductServiceNew;
import com.ecommerce.service.ShoppingCartService;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service
public class ShoppingCartServiceImpl implements ShoppingCartService {

    private final ShoppingCartRepository shoppingCartRepository;
    private final ProductService productService;
    private final ProductServiceNew productServiceNew;
    private final CartItemRepository cartItemRepository;

    public ShoppingCartServiceImpl(ShoppingCartRepository shoppingCartRepository, ProductService productService, ProductServiceNew productServiceNew, CartItemRepository cartItemRepository) {
        this.shoppingCartRepository = shoppingCartRepository;
        this.productService = productService;
        this.productServiceNew = productServiceNew;
        this.cartItemRepository = cartItemRepository;
    }

    @Override
    public ShoppingCart addShoppingCartFirstTime(String productId, String sessionToken, int quantity) {
        // create object CartItem (Keranjang tiap item product)
        CartItem cartItem = new CartItem();
        cartItem.setQuantity(quantity);
        cartItem.setDate(LocalDate.now());
        cartItem.setProduct(productService.getProductId(productId));

        // create object ShoppingCart
        ShoppingCart shoppingCart = new ShoppingCart();
        shoppingCart.getItems().add(cartItem);
        shoppingCart.setSessionToken(sessionToken);
        shoppingCart.setDate(LocalDate.now());
        return shoppingCartRepository.save(shoppingCart);
    }

    @Override
    public ShoppingCart addToExistingShoppingCart(String shoppingCartId, String sessionToken, int quantity) {
//        ShoppingCart shoppingCart = shoppingCartRepository.findBySessionToken(sessionToken);
//        Product p = productService.getProductById(id);
//        Boolean productDoesExistInTheCart = false;
//        if (shoppingCart != null) {
//            Set<CartItem> items = shoppingCart.getItems();
//            for (CartItem item : items) {
//                if (item.getProduct().equals(p)) {
//                    productDoesExistInTheCart = true;
//                    item.setQuantity(item.getQuantity() + quantity);
//                    shoppingCart.setItems(items);
//                    return shoppingCartRepository.saveAndFlush(shoppingCart);
//                }
//
//            }
//        }
//        if (!productDoesExistInTheCart && (shoppingCart != null)) {
//            CartItem cartItem1 = new CartItem();
//            cartItem1.setDate(new Date());
//            cartItem1.setQuantity(quantity);
//            cartItem1.setProduct(p);
//            shoppingCart.getItems().add(cartItem1);
//            return shoppingCartRepository.saveAndFlush(shoppingCart);
//        }
//
//        return this.addShoppingCartFirstTime(id, sessionToken, quantity);
        return null;
    }

    @Override
    public ShoppingCart getShoppingCartBySessionToken(String sessionToken) {
        return shoppingCartRepository.findBySessionToken(sessionToken);
    }

    @Override
    public CartItem updateShoppingCartItem(String shoppingCartId, int quantity) {
//        CartItem cartItem = cartItemRepository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Product with " + id + " not found"));
//        cartItem.setQuantity(quantity);
//        return cartItemRepository.saveAndFlush(cartItem);
        return null;
    }

    @Override
    public ShoppingCart removeCartIemFromShoppingCart(String shoppingCartId, String sessionToken) {
//        ShoppingCart shoppingCart = shoppingCartRepository.findBySessionToken(sessionToken);
//        Set<CartItem> items = shoppingCart.getItems();
//        CartItem cartItem = null;
//        for (CartItem item : items) {
//            if (item.getId() == id) {
//                cartItem = item;
//            }
//        }
//        items.remove(cartItem);
//        cartItemRepository.delete(cartItem);
//        shoppingCart.setItems(items);
//        return shoppingCartRepository.save(shoppingCart);
        return null;
    }

    @Override
    public void clearShoppingCart(String sessionToken) {
        ShoppingCart sh = shoppingCartRepository.findBySessionToken(sessionToken);
        shoppingCartRepository.delete(sh);
    }
}
