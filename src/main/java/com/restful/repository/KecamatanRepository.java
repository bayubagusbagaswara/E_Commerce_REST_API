package com.restful.repository;

import com.restful.entity.region.SubDistrict;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface KecamatanRepository extends JpaRepository<SubDistrict, String> {

    Optional<SubDistrict> findAllByCode(String code);
    Optional<SubDistrict> findAllByNameIgnoreCase(String name);
    List<SubDistrict> findAllByNameContainingIgnoreCase(String name);
    List<SubDistrict> findAllByKotaId(String kodaId);
}
