package com.ecommerce.repository;

import com.ecommerce.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

public interface ProductRepository extends JpaRepository<Product, String> {

    Optional<Product> findProductByNameIgnoreCase(String name);
    List<Product> findAllByNameContainingIgnoreCase(String name);
    Optional<Product> findAllByProductDetailSku(String sku);
    List<Product> findAllByPriceBetween(BigDecimal priceMin, BigDecimal priceMax);
    List<Product> findAllByNameContainingAndPriceBetween(String name, BigDecimal priceMin, BigDecimal priceMax);
    List<Product> findAllByCategoryId(String categoryId);
    List<Product> findAllBySuppliersId(String supplierId);

    //    List<Product> findByNameContainingIgnoreCase(String name);
//
//    @Query("SELECT DISTINCT brand FROM Product")
//    List<String> findAllBrandsDistincts();

}
