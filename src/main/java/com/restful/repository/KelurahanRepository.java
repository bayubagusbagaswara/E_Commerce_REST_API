package com.restful.repository;

import com.restful.entity.wilayah.Kelurahan;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface KelurahanRepository extends JpaRepository<Kelurahan, String> {

    Optional<Kelurahan> findAllByCode(String code);
    Optional<Kelurahan> findAllByNameIgnoreCase(String name);
    List<Kelurahan> findAllByNameContainingIgnoreCase(String name);
    List<Kelurahan> findAllByKecamatanId(String kecamatanId);
}
