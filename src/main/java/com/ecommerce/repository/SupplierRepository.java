package com.ecommerce.repository;

import com.ecommerce.entity.Supplier;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface SupplierRepository extends JpaRepository<Supplier, String> {

    Optional<Supplier> findAllByNameIgnoreCase(String name);

    Optional<Supplier> findAllByEmail(String email);

    List<Supplier> findAllByNameContainingIgnoreCase(String name);

    List<Supplier> findAllByProductsId(String productId);
}
