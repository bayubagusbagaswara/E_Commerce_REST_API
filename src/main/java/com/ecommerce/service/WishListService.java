package com.ecommerce.service;

import com.ecommerce.entity.WishList;

public interface WishListService {

    WishList addToWishFirstTime(Long id, String sessionToken);

    WishList addToExistingWishList(Long id, String sessionToken);

    WishList getWishListBySessionToken(String sessionToken);

    WishList removeItemWishList(Long id, String sessionToken);

    void clearWishList(String sessionToken);
}
