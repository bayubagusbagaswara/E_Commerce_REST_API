package com.restful.repository;

import com.restful.entity.address.Kecamatan;
import org.springframework.data.jpa.repository.JpaRepository;

public interface KecamatanRepository extends JpaRepository<Kecamatan, String> {
}
