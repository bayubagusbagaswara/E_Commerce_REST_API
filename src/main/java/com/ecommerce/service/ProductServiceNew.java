package com.ecommerce.service;

import com.ecommerce.entity.Product;
import org.springframework.web.multipart.MultipartFile;

public interface ProductServiceNew {

    void saveProductToDatabase(MultipartFile file, String name, String description, int quantity, Double price, String brand, String categories);
    Product addCategoriesToProduct(Product p, String categories);
}
