package com.ecommerce.service.region;

import com.ecommerce.dto.region.district.*;
import com.ecommerce.exception.KotaNotFoundException;
import com.ecommerce.exception.ProvinceNotFoundException;

import java.util.List;

public interface DistrictService {

    DistrictDTO createDistrict(CreateDistrictRequestDTO createKotaRequest) throws ProvinceNotFoundException;

    DistrictDTO getKotaById(String id) throws KotaNotFoundException;

    ListDistrictResponseDTO getAllKota(ListDistrictRequestDTO listKotaRequest);

    DistrictDTO updateKota(String id, UpdateDistrictRequestDTO updateKotaRequest) throws KotaNotFoundException, ProvinceNotFoundException;

    void deleteKota(String id) throws KotaNotFoundException;

    DistrictDTO getKotaByName(String name) throws KotaNotFoundException;

    List<DistrictDTO> getKotaByNameContains(String name);

    DistrictDTO getKotaByCode(String code) throws KotaNotFoundException;

    List<DistrictDTO> getKotaByProvinsiId(String provinsiId);
}
