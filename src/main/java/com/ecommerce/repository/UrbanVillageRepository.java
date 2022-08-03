package com.ecommerce.repository;

import com.ecommerce.entity.region.SubDistrict;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UrbanVillageRepository extends JpaRepository<SubDistrict, String> {

    Optional<SubDistrict> findAllByCode(String code);
    Optional<SubDistrict> findAllByNameIgnoreCase(String name);
    List<SubDistrict> findAllByNameContainingIgnoreCase(String name);
    List<SubDistrict> findAllByKotaId(String districtId);
}
