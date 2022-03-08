package com.restful.service;

import com.restful.dto.kota.*;
import com.restful.exception.KotaNotFoundException;

import java.util.List;

public interface KotaService {

    // create kota
    KotaResponseDto createKota(CreateKotaRequestDto createKotaRequestDto);

    // get kota by id
    KotaResponseDto getKotaById(String id) throws KotaNotFoundException;

    // get all kota
    ListKotaResponseDto getAllKota(ListKotaRequestDto listKotaRequestDto);

    // update kota
    KotaResponseDto updateKota(String id, UpdateKotaRequestDto updateKotaRequestDto) throws KotaNotFoundException;

    // delete kota by id
    void deleteKota(String id);

    // get Kota by name
    KotaResponseDto getKotaByName(String name) throws KotaNotFoundException;

    // get kota by name contains
    List<KotaResponseDto> getKotaByNameContains(String name);

    // get kota by code
    KotaResponseDto getKotaByCode(String code) throws KotaNotFoundException;

    // get kota by provinsi id, balikan adalah List of Kota
    List<KotaResponseDto> getKotaByProvinsiId(String provinsiId);
}
