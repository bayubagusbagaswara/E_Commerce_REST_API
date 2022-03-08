package com.restful.service;

import com.restful.dto.kota.*;
import com.restful.exception.KotaNotFoundException;
import com.restful.exception.ProvinsiNotFoundException;

import java.util.List;

public interface KotaService {

    KotaResponseDto createKota(CreateKotaRequestDto createKotaRequestDto) throws ProvinsiNotFoundException;

    KotaResponseDto getKotaById(String id) throws KotaNotFoundException;

    ListKotaResponseDto getAllKota(ListKotaRequestDto listKotaRequestDto);

    KotaResponseDto updateKota(String id, UpdateKotaRequestDto updateKotaRequestDto) throws KotaNotFoundException, ProvinsiNotFoundException;

    void deleteKota(String id);

    KotaResponseDto getKotaByName(String name) throws KotaNotFoundException;

    List<KotaResponseDto> getKotaByNameContains(String name);

    KotaResponseDto getKotaByCode(String code) throws KotaNotFoundException;

    List<KotaResponseDto> getKotaByProvinsiId(String provinsiId);
}
