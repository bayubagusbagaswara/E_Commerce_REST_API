package com.ecommerce.service.region;

import com.ecommerce.dto.provinsi.*;
import com.ecommerce.dto.region.province.*;
import com.ecommerce.exception.ProvinsiNotFoundException;

import java.util.List;

public interface ProvinceService {

    ProvinsiResponseDto createProvinsi(CreateProvinsiRequestDto createProvinsiRequest);

    ProvinsiResponseDto getProvinsiById(String id) throws ProvinsiNotFoundException;

    ListProvinsiResponseDto getAllProvinsi(ListProvinsiRequestDto listProvinsiRequest);

    ProvinsiResponseDto updateProvinsi(String id, UpdateProvinsiRequestDto updateProvinsiRequest) throws ProvinsiNotFoundException;

    void deleteProvinsi(String id) throws ProvinsiNotFoundException;

    ProvinsiResponseDto getProvinsiByName(String name) throws ProvinsiNotFoundException;

    List<ProvinsiResponseDto> getProvinsiByNameContains(String name);

    ProvinsiResponseDto getProvinsiByCode(String code) throws ProvinsiNotFoundException;
}
