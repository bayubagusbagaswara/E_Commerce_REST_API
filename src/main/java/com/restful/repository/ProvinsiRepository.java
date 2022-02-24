package com.restful.repository;

import com.restful.entity.address.Provinsi;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProvinsiRepository extends JpaRepository<Provinsi, String> {

    // get provinsi by code

    // get provinsi by name ignore case

    // get provinsi by name containing ignore case
}
