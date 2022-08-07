package com.ecommerce.service;

import com.ecommerce.entity.Category;
import com.ecommerce.entity.Product;
import org.springframework.web.multipart.MultipartFile;

import java.awt.image.BufferedImage;
import java.util.List;

public interface ProductServiceNew {

    void saveProductToDatabase(MultipartFile file, String name, String description, int quantity, Double price, String brand, String categories);
    Product addCategoriesToProduct(Product p, String categories);

    List<Product> getAllProducts();

    void deleteProductById(String productId);

    void changeProductName(String productId, String name);

    void changeProductDescription(String productId, String description);

    void changeProductPrice(String productId, Double price);

    Category saveCategory(Category category);

    List<Category> getAllCategories();

    void addImageToProduct(MultipartFile file, String productId);

    void changeProductQuantity(String productId, int quantity);

    void saveProductDiscount(String productId, int discount);

    void changeProductDiscount(String productId, int discount);

    String resizeImageForUse(String src, int width, int height);

    BufferedImage resizeImage(BufferedImage image, int width, int height);

    BufferedImage base64ToBufferedImage(String base64Img);

    String bufferedImageToBase64(BufferedImage image);

    Product getProductById(String id);

    List<Product> searchProductByNameLike(String value);

    List<String> getAllBrands();

    Product getProductWithBiggestDiscount();

}
