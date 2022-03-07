package com.restful.service;

import com.restful.dto.provinsi.*;
import com.restful.exception.ProvinsiNotFoundException;

import java.util.List;

public interface ProvinsiService {

    // create provinsi
    ProvinsiResponseDto createProvinsi(CreateProvinsiRequestDto createProvinsiRequestDto);

    // get provinsi by id
    ProvinsiResponseDto getProvinsiById(String id) throws ProvinsiNotFoundException;

    // get all provinsi
    ListProvinsiResponseDto getAllProvinsi(ListProvinsiRequestDto listProvinsiRequestDto);

    // update provinsi
    ProvinsiResponseDto updateProvinsi(String id, UpdateProvinsiRequestDto updateProvinsiRequestDto) throws ProvinsiNotFoundException;

    // delete provinsi
    void deleteProvinsi(String id);

    ProvinsiResponseDto getProvinsiByName(String name) throws ProvinsiNotFoundException;

    // get provinsi by name contains
    List<ProvinsiResponseDto> getProvinsiByNameContains(String name);

    // get provinsi by code
    ProvinsiResponseDto getProvinsiByCode(String code) throws ProvinsiNotFoundException;
}
