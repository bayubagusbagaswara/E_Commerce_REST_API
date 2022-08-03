package com.ecommerce.repository;

import com.ecommerce.entity.ProductDetail;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ProductDetailRepository extends JpaRepository<ProductDetail, String> {

    Optional<ProductDetail> findAllBySku(String sku);
}
