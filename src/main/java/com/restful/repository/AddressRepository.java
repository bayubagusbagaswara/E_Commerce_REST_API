package com.restful.repository;

import com.restful.entity.Address;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface AddressRepository extends JpaRepository<Address, String> {

    // find by postal code
    Optional<Address> findAllByPostalCode(String postalCode);
}
