package com.restful.service;

import com.restful.dto.provinsi.*;
import com.restful.dto.region.provinsi.*;
import com.restful.exception.ProvinsiNotFoundException;

import java.util.List;

public interface ProvinsiService {

    ProvinsiResponseDto createProvinsi(CreateProvinsiRequestDto createProvinsiRequest);

    ProvinsiResponseDto getProvinsiById(String id) throws ProvinsiNotFoundException;

    ListProvinsiResponseDto getAllProvinsi(ListProvinsiRequestDto listProvinsiRequest);

    ProvinsiResponseDto updateProvinsi(String id, UpdateProvinsiRequestDto updateProvinsiRequest) throws ProvinsiNotFoundException;

    void deleteProvinsi(String id) throws ProvinsiNotFoundException;

    ProvinsiResponseDto getProvinsiByName(String name) throws ProvinsiNotFoundException;

    List<ProvinsiResponseDto> getProvinsiByNameContains(String name);

    ProvinsiResponseDto getProvinsiByCode(String code) throws ProvinsiNotFoundException;
}
