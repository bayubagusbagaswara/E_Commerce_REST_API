package com.ecommerce.repository;

import com.ecommerce.entity.Supplier;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface SupplierRepository extends JpaRepository<Supplier, String> {

    // get supplier by name ignore case
    Optional<Supplier> findAllByNameIgnoreCase(String name);

    // get supplier by email
    Optional<Supplier> findAllByEmail(String email);

    // get all supplier by name containing ignore case
    List<Supplier> findAllByNameContainingIgnoreCase(String name);

    // get all supplier by product id
    List<Supplier> findAllByProductsId(String productId);
}
