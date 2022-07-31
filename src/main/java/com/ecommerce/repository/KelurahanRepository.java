package com.ecommerce.repository;

import com.ecommerce.entity.region.UrbanVillage;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface KelurahanRepository extends JpaRepository<UrbanVillage, String> {

    Optional<UrbanVillage> findAllByCode(String code);
    Optional<UrbanVillage> findAllByNameIgnoreCase(String name);
    List<UrbanVillage> findAllByNameContainingIgnoreCase(String name);
    List<UrbanVillage> findAllByKecamatanId(String kecamatanId);
}
