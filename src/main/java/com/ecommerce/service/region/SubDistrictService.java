package com.ecommerce.service.region;

import com.ecommerce.dto.kecamatan.*;
import com.ecommerce.dto.region.subDistrict.*;
import com.ecommerce.exception.KecamatanNotFoundException;
import com.ecommerce.exception.KotaNotFoundException;

import java.util.List;

public interface SubDistrictService {

    KecamatanResponseDto createKecamatan(CreateSubDistrictRequestDTO createKecamatanRequest) throws KotaNotFoundException;

    KecamatanResponseDto getKecamatanById(String id) throws KecamatanNotFoundException;

    ListKecamatanResponseDto getAllKecamatan(ListKecamatanRequestDto listKecamatanRequest);

    KecamatanResponseDto updateKecamatan(String id, UpdateKecamatanRequestDto updateKecamatanRequest) throws KecamatanNotFoundException, KotaNotFoundException;

    void deleteKecamatan(String id) throws KecamatanNotFoundException;

    KecamatanResponseDto getKecamatanByName(String name) throws KecamatanNotFoundException;

    List<KecamatanResponseDto> getKecamatanByNameContains(String name);

    KecamatanResponseDto getKecamatanByCode(String code) throws KecamatanNotFoundException;

    List<KecamatanResponseDto> getKecamatanByKotaId(String kotaId);
}
