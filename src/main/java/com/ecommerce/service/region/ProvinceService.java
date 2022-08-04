package com.ecommerce.service.region;

import com.ecommerce.dto.region.province.*;
import com.ecommerce.exception.ProvinceNotFoundException;

import java.util.List;

public interface ProvinceService {

    ProvinceDTO createProvince(CreateProvinceRequestDTO createProvinceRequestDTO);

    ProvinceDTO getProvinceById(String provinceId) throws ProvinceNotFoundException;

    ListProvinceResponseDTO getAllProvinces(ListProvinceRequestDTO listProvinceRequestDTO);

    ProvinceDTO updateProvince(String provinceId, UpdateProvinceRequestDTO updateProvinceRequestDTO) throws ProvinceNotFoundException;

    void deleteProvince(String provinceId) throws ProvinceNotFoundException;

    ProvinceDTO getProvinceByName(String name) throws ProvinceNotFoundException;

    List<ProvinceDTO> getProvinceByNameContains(String name);

    ProvinceDTO getProvinceByCode(String code) throws ProvinceNotFoundException;
}
