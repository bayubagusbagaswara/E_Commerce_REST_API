package com.ecommerce.service;

import com.ecommerce.entity.WishList;

public interface WishListService {

    WishList addToWishFirstTime(String productId, String sessionToken);

    WishList addToExistingWishList(String productId, String sessionToken);

    WishList getWishListBySessionToken(String sessionToken);

    WishList removeItemWishList(String productId, String sessionToken);

    void clearWishList(String sessionToken);
}
