package com.restful.repository;

import com.restful.entity.address.Kecamatan;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface KecamatanRepository extends JpaRepository<Kecamatan, String> {

    Optional<Kecamatan> findAllByCode(String code);
    Optional<Kecamatan> findAllByNameIgnoreCase(String name);
    List<Kecamatan> findAllByNameContainingIgnoreCase(String name);
}
