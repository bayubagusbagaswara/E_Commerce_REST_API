package com.ecommerce.service;

import com.ecommerce.entity.product.Product;
import org.springframework.web.multipart.MultipartFile;

import java.awt.image.BufferedImage;

public interface ProductServiceNew {

    void saveProductToDatabase(MultipartFile file, String name, String description, int quantity, Double price, String brand, String categories);

    Product addCategoriesToProduct(Product p, String categories);

    void addImageToProduct(MultipartFile file, String productId);

    void changeProductQuantity(String productId, int quantity);

    void saveProductDiscount(String productId, int discount);

    String resizeImageForUse(String src, int width, int height);

    BufferedImage resizeImage(BufferedImage image, int width, int height);

    BufferedImage base64ToBufferedImage(String base64Img);

    String bufferedImageToBase64(BufferedImage image);

    Product getProductWithBiggestDiscount();

}
