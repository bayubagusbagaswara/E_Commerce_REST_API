package com.restful.repository;

import com.restful.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;

import java.math.BigDecimal;
import java.util.List;

public interface ProductRepository extends JpaRepository<Product, String> {

    // get product by name containing ignore case
    List<Product> findAllByNameContainingIgnoreCase(String name);

    // get product by price between
    List<Product> findAllByPriceBetween(BigDecimal priceMin, BigDecimal priceMax);

    // get product by name containing and price between
    List<Product> findAllByNameContainingAndPriceBetween(String name, BigDecimal priceMin, BigDecimal priceMax);

    // get product by category id
    List<Product> findAllByCategoryId(String categoryId);

    // get product by supplier id
    List<Product> findAllBySuppliersId(String supplierId);
}
