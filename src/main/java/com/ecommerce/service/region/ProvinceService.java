package com.ecommerce.service.region;

import com.ecommerce.dto.provinsi.*;
import com.ecommerce.dto.region.province.*;
import com.ecommerce.exception.ProvinsiNotFoundException;

import java.util.List;

public interface ProvinceService {

    ProvinceDTO createProvinsi(CreateProvinceRequestDTO createProvinsiRequest);

    ProvinceDTO getProvinsiById(String id) throws ProvinsiNotFoundException;

    ListProvinceResponseDTO getAllProvinsi(ListProvinceRequestDTO listProvinsiRequest);

    ProvinceDTO updateProvinsi(String id, UpdateProvinceRequestDTO updateProvinsiRequest) throws ProvinsiNotFoundException;

    void deleteProvinsi(String id) throws ProvinsiNotFoundException;

    ProvinceDTO getProvinsiByName(String name) throws ProvinsiNotFoundException;

    List<ProvinceDTO> getProvinsiByNameContains(String name);

    ProvinceDTO getProvinsiByCode(String code) throws ProvinsiNotFoundException;
}
