package com.ecommerce.service.region;

import com.ecommerce.dto.region.province.*;

import java.util.List;

public interface ProvinceService {

    ProvinceDTO createProvince(CreateProvinceRequestDTO createProvinceRequestDTO);

    ProvinceDTO getProvinceById(String provinceId);

    ListProvinceResponseDTO getAllProvinces(ListProvinceRequestDTO listProvinceRequestDTO);

    ProvinceDTO updateProvince(String provinceId, UpdateProvinceRequestDTO updateProvinceRequestDTO);

    void deleteProvince(String provinceId);

    ProvinceDTO getProvinceByName(String name);

    List<ProvinceDTO> getProvinceByNameContains(String name);

    ProvinceDTO getProvinceByCode(String code);
}
