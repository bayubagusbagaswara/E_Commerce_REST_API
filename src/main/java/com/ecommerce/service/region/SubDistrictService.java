package com.ecommerce.service.region;

import com.ecommerce.dto.kecamatan.*;
import com.ecommerce.dto.region.subDistrict.*;
import com.ecommerce.exception.KecamatanNotFoundException;
import com.ecommerce.exception.KotaNotFoundException;

import java.util.List;

public interface SubDistrictService {

    SubDistrictDTO createKecamatan(CreateSubDistrictRequestDTO createKecamatanRequest) throws KotaNotFoundException;

    SubDistrictDTO getKecamatanById(String id) throws KecamatanNotFoundException;

    ListSubDistrictResponseDTO getAllKecamatan(ListSubDistrictRequestDTO listKecamatanRequest);

    SubDistrictDTO updateKecamatan(String id, UpdateKecamatanRequestDto updateKecamatanRequest) throws KecamatanNotFoundException, KotaNotFoundException;

    void deleteKecamatan(String id) throws KecamatanNotFoundException;

    SubDistrictDTO getKecamatanByName(String name) throws KecamatanNotFoundException;

    List<SubDistrictDTO> getKecamatanByNameContains(String name);

    SubDistrictDTO getKecamatanByCode(String code) throws KecamatanNotFoundException;

    List<SubDistrictDTO> getKecamatanByKotaId(String kotaId);
}
