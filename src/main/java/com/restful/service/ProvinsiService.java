package com.restful.service;

import com.restful.dto.provinsi.*;
import com.restful.exception.ProvinsiNotFoundException;

import java.util.List;

public interface ProvinsiService {

    ProvinsiResponseDto createProvinsi(CreateProvinsiRequestDto createProvinsiRequestDto);

    ProvinsiResponseDto getProvinsiById(String id) throws ProvinsiNotFoundException;

    ListProvinsiResponseDto getAllProvinsi(ListProvinsiRequestDto listProvinsiRequestDto);

    ProvinsiResponseDto updateProvinsi(String id, UpdateProvinsiRequestDto updateProvinsiRequestDto) throws ProvinsiNotFoundException;

    void deleteProvinsi(String id);

    ProvinsiResponseDto getProvinsiByName(String name) throws ProvinsiNotFoundException;

    List<ProvinsiResponseDto> getProvinsiByNameContains(String name);

    ProvinsiResponseDto getProvinsiByCode(String code) throws ProvinsiNotFoundException;
}
