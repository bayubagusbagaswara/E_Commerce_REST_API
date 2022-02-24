package com.restful.repository;

import com.restful.entity.address.Kota;
import org.springframework.data.jpa.repository.JpaRepository;

public interface KotaRepository extends JpaRepository<Kota, String> {

    // get kota by name ignore case
    // get kota by code
    // get kota by name containing
}
