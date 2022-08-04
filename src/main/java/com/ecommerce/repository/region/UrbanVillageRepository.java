package com.ecommerce.repository.region;

import com.ecommerce.entity.region.UrbanVillage;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UrbanVillageRepository extends JpaRepository<UrbanVillage, String> {

    Optional<UrbanVillage> findAllByCode(String code);
    Optional<UrbanVillage> findAllByNameIgnoreCase(String name);
    List<UrbanVillage> findAllByNameContainingIgnoreCase(String name);
    List<UrbanVillage> findAllBySubDistrictId(String SubDistrictId);
}
