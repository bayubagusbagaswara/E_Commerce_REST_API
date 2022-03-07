package com.restful.repository;

import com.restful.entity.address.Kota;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface KotaRepository extends JpaRepository<Kota, String> {

    Optional<Kota> findAllByCode(String code);
    Optional<Kota> findAllByNameIgnoreCase(String name);
    List<Kota> findAllByNameContainingIgnoreCase(String name);
}
