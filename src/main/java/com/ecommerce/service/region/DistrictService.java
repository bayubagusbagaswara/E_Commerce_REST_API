package com.ecommerce.service.region;

import com.ecommerce.dto.region.district.*;

import java.util.List;

public interface DistrictService {

    DistrictDTO createDistrict(CreateDistrictRequestDTO districtDTO);

    DistrictDTO getDistrictById(String districtId);

    ListDistrictResponseDTO getAllDistricts(ListDistrictRequestDTO districtRequestDTO);

    DistrictDTO updateDistrict(String districtId, UpdateDistrictRequestDTO updateDistrictRequest);

    void deleteDistrict(String districtId);

    DistrictDTO getDistrictByName(String name);

    List<DistrictDTO> getDistrictByNameContains(String name);

    DistrictDTO getDistrictByCode(String code);

    List<DistrictDTO> getAllDistrictsByProvinceId(String provinceId);
}
