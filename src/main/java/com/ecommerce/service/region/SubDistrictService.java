package com.ecommerce.service.region;

import com.ecommerce.dto.region.subDistrict.*;

import java.util.List;

public interface SubDistrictService {

    SubDistrictDTO createSubDistrict(CreateSubDistrictRequestDTO createSubDistrictRequestDTO);

    SubDistrictDTO getSubDistrictById(String subDistrictId);

    ListSubDistrictResponseDTO getAllSubDistricts(ListSubDistrictRequestDTO listSubDistrictRequestDTO);

    SubDistrictDTO updateSubDistrict(String subDistrictId, UpdateSubDistrictRequestDTO updateSubDistrictRequestDTO);

    void deleteSubDistrict(String subDistrictId);

    SubDistrictDTO getSubDistrictByName(String name);

    List<SubDistrictDTO> getSubDistrictByNameContains(String name);

    SubDistrictDTO getSubDistrictByCode(String code);

    List<SubDistrictDTO> getAllSubDistrictsByDistrictId(String subDistrictId);
}
