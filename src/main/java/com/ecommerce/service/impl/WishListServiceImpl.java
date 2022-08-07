package com.ecommerce.service.impl;

import com.ecommerce.entity.Product;
import com.ecommerce.entity.WishList;
import com.ecommerce.entity.WishListItem;
import com.ecommerce.repository.WishListItemRepository;
import com.ecommerce.repository.WishListRepository;
import com.ecommerce.service.ProductService;
import com.ecommerce.service.WishListService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.Set;

@Service
@Slf4j
@RequiredArgsConstructor
public class WishListServiceImpl implements WishListService {

    private final WishListRepository wishListRepository;
    private final WishListItemRepository wishListItemRepository;
    private final ProductService productService;

    @Override
    public WishList addToWishFirstTime(String productId, String sessionToken) {
        WishList wishlist = new WishList();
        WishListItem item = new WishListItem();

        item.setDate(LocalDate.now());
        item.setProduct(productService.getProductId(productId));
        wishlist.getItems().add(item);
        wishlist.setSessionToken(sessionToken);
        wishlist.setDate(LocalDate.now());
        return wishListRepository.save(wishlist);
    }

    @Override
    public WishList addToExistingWishList(String productId, String sessionToken) {
        WishList wishList = wishListRepository.findBySessionToken(sessionToken);
        Product product = productService.getProductId(productId);
        Boolean productDoesExistInTheCart = false;
        if (wishList != null) {
            Set<WishListItem> items = wishList.getItems();
            for (WishListItem item : items) {
                if (item.getProduct().equals(product)) {
                    productDoesExistInTheCart = true;
                    break;
                }

            }
        }
        if (!productDoesExistInTheCart && (wishList != null)) {
            WishListItem item1 = new WishListItem();
            item1.setDate(LocalDate.now());
            item1.setProduct(product);
            wishList.getItems().add(item1);
            return wishListRepository.saveAndFlush(wishList);
        }
        return null;
    }

    public WishList getWishListBySessionToken(String sessionToken) {
        return wishListRepository.findBySessionToken(sessionToken);
    }

    public WishList removeItemWishList(String productId, String sessionToken) {
        WishList WishList = wishListRepository.findBySessionToken(sessionToken);
        Set<WishListItem> items = WishList.getItems();
        WishListItem item = null;
        for (WishListItem item1 : items) {
            if (item1.getId() == productId) {
                item = item1;
            }
        }
        items.remove(item);
        wishListItemRepository.delete(item);
        WishList.setItems(items);
        return wishListRepository.save(WishList);
    }

    public void clearWishList(String sessionToken) {
        WishList sh = wishListRepository.findBySessionToken(sessionToken);
        wishListRepository.delete(sh);
    }
}
