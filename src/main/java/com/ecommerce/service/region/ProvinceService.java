package com.ecommerce.service.region;

import com.ecommerce.dto.region.province.*;
import com.ecommerce.exception.ProvinsiNotFoundException;

import java.util.List;

public interface ProvinceService {

    ProvinceDTO createProvince(CreateProvinceRequestDTO createProvinceRequestDTO);

    ProvinceDTO getProvinceById(String provinceId) throws ProvinsiNotFoundException;

    ListProvinceResponseDTO getAllProvinces(ListProvinceRequestDTO listProvinceRequestDTO);

    ProvinceDTO updateProvince(String provinceId, UpdateProvinceRequestDTO updateProvinceRequestDTO) throws ProvinsiNotFoundException;

    void deleteProvince(String provinceId) throws ProvinsiNotFoundException;

    ProvinceDTO getProvinceByName(String name) throws ProvinsiNotFoundException;

    List<ProvinceDTO> getProvinceByNameContains(String name);

    ProvinceDTO getProvinceByCode(String code) throws ProvinsiNotFoundException;
}
