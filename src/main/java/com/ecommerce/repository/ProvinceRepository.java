package com.ecommerce.repository;

import com.ecommerce.entity.region.Province;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ProvinceRepository extends JpaRepository<Province, String> {

    Optional<Province> findAllByCode(String code);

    Optional<Province> findAllByNameIgnoreCase(String name);

    List<Province> findAllByNameContainingIgnoreCase(String name);

    Page<Province> findAllByNameContainsIgnoreCase(String name, Pageable pageable);
}
