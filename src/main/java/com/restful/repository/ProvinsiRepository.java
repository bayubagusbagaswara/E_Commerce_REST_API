package com.restful.repository;

import com.restful.entity.region.Province;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface ProvinsiRepository extends JpaRepository<Province, String> {

    // get provinsi by code
    Optional<Province> findAllByCode(String code);

    // get provinsi by name ignore case
    Optional<Province> findAllByNameIgnoreCase(String name);

    // get provinsi by name containing ignore case
    List<Province> findAllByNameContainingIgnoreCase(String name);

    Page<Province> findAllByNameContainsIgnoreCase(String name, Pageable pageable);
}
