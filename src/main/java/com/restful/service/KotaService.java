package com.restful.service;

import com.restful.dto.kota.*;
import com.restful.dto.region.kota.*;
import com.restful.exception.KotaNotFoundException;
import com.restful.exception.ProvinsiNotFoundException;

import java.util.List;

public interface KotaService {

    KotaResponseDto createKota(CreateKotaRequestDto createKotaRequest) throws ProvinsiNotFoundException;

    KotaResponseDto getKotaById(String id) throws KotaNotFoundException;

    ListKotaResponseDto getAllKota(ListKotaRequestDto listKotaRequest);

    KotaResponseDto updateKota(String id, UpdateKotaRequestDto updateKotaRequest) throws KotaNotFoundException, ProvinsiNotFoundException;

    void deleteKota(String id) throws KotaNotFoundException;

    KotaResponseDto getKotaByName(String name) throws KotaNotFoundException;

    List<KotaResponseDto> getKotaByNameContains(String name);

    KotaResponseDto getKotaByCode(String code) throws KotaNotFoundException;

    List<KotaResponseDto> getKotaByProvinsiId(String provinsiId);
}
