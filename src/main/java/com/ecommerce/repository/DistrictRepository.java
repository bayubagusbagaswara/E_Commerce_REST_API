package com.ecommerce.repository;

import com.ecommerce.entity.region.District;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface DistrictRepository extends JpaRepository<District, String> {

    Optional<District> findAllByCode(String code);
    Optional<District> findAllByNameIgnoreCase(String name);
    List<District> findAllByNameContainingIgnoreCase(String name);
    List<District> findAllByProvinceId(String provinceId);
}
