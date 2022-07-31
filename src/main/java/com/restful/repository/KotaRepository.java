package com.restful.repository;

import com.restful.entity.region.District;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface KotaRepository extends JpaRepository<District, String> {

    Optional<District> findAllByCode(String code);
    Optional<District> findAllByNameIgnoreCase(String name);
    List<District> findAllByNameContainingIgnoreCase(String name);
    List<District> findAllByProvinsiId(String provinsiId);
}
