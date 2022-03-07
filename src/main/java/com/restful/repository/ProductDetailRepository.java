package com.restful.repository;

import com.restful.entity.ProductDetail;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ProductDetailRepository extends JpaRepository<ProductDetail, String> {

    // get product detail by SKU
    Optional<ProductDetail> findAllBySku(String sku);
}
