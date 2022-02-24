package com.restful.repository;

import com.restful.entity.address.Kelurahan;
import org.springframework.data.jpa.repository.JpaRepository;

public interface KelurahanRepository extends JpaRepository<Kelurahan, String> {

    // get kelurahan by postal code
    // get kelurahan by name ignore case
    // get kelurahan by code
    // get kelurahan by name containing ignore case
}
