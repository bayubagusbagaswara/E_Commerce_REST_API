package com.restful.repository;

import com.restful.entity.Supplier;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SupplierRepository extends JpaRepository<Supplier, String> {

    // get supplier by name ignore case
    // get supplier by email
    // get all supplier by name containing ignore case
    // get all supplier by product id
}
