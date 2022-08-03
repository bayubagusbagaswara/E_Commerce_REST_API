package com.ecommerce.service.region;

import com.ecommerce.dto.kota.*;
import com.ecommerce.dto.region.district.*;
import com.ecommerce.exception.KotaNotFoundException;
import com.ecommerce.exception.ProvinsiNotFoundException;

import java.util.List;

public interface DistrictService {

    DistrictDTO createKota(CreateDistrictRequestDTO createKotaRequest) throws ProvinsiNotFoundException;

    DistrictDTO getKotaById(String id) throws KotaNotFoundException;

    ListDistrictResponseDTO getAllKota(ListDistrictRequestDTO listKotaRequest);

    DistrictDTO updateKota(String id, UpdateKotaRequestDto updateKotaRequest) throws KotaNotFoundException, ProvinsiNotFoundException;

    void deleteKota(String id) throws KotaNotFoundException;

    DistrictDTO getKotaByName(String name) throws KotaNotFoundException;

    List<DistrictDTO> getKotaByNameContains(String name);

    DistrictDTO getKotaByCode(String code) throws KotaNotFoundException;

    List<DistrictDTO> getKotaByProvinsiId(String provinsiId);
}
