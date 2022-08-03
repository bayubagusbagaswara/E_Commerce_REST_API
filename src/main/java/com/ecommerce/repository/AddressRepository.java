package com.ecommerce.repository;

import com.ecommerce.entity.SupplierAddress;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AddressRepository extends JpaRepository<SupplierAddress, String> {

}
