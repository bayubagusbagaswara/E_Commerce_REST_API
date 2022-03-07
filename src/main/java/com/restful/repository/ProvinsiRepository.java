package com.restful.repository;

import com.restful.entity.address.Provinsi;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface ProvinsiRepository extends JpaRepository<Provinsi, String> {

    // get provinsi by code
    Optional<Provinsi> findAllByCode(String code);

    // get provinsi by name ignore case
    Optional<Provinsi> findAllByNameIgnoreCase(String name);

    // get provinsi by name containing ignore case
    List<Provinsi> findAllByNameContainingIgnoreCase(String name);
}
