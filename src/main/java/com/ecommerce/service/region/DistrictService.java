package com.ecommerce.service.region;

import com.ecommerce.dto.region.district.*;
import com.ecommerce.exception.DistrictNotFoundException;
import com.ecommerce.exception.ProvinceNotFoundException;

import java.util.List;

public interface DistrictService {

    DistrictDTO createDistrict(CreateDistrictRequestDTO createKotaRequest) throws ProvinceNotFoundException;

    DistrictDTO getKotaById(String id) throws DistrictNotFoundException;

    ListDistrictResponseDTO getAllKota(ListDistrictRequestDTO listKotaRequest);

    DistrictDTO updateKota(String id, UpdateDistrictRequestDTO updateKotaRequest) throws DistrictNotFoundException, ProvinceNotFoundException;

    void deleteKota(String id) throws DistrictNotFoundException;

    DistrictDTO getKotaByName(String name) throws DistrictNotFoundException;

    List<DistrictDTO> getKotaByNameContains(String name);

    DistrictDTO getKotaByCode(String code) throws DistrictNotFoundException;

    List<DistrictDTO> getKotaByProvinsiId(String provinsiId);
}
