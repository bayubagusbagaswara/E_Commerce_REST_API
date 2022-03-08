package com.restful.service;

import com.restful.dto.kecamatan.*;
import com.restful.exception.KecamatanNotFoundException;
import com.restful.exception.KotaNotFoundException;

import java.util.List;

public interface KecamatanService {

    // create kecamatan
    KecamatanResponseDto createKecamatan(CreateKecamatanRequestDto createKecamatanRequestDto) throws KotaNotFoundException;

    // get kecamatan by id
    KecamatanResponseDto getKecamatanById(String id) throws KecamatanNotFoundException;

    // get all kecamatan
    ListKecamatanResponseDto getAllKecamatan(ListKecamatanRequestDto listKecamatanRequestDto);

    // update kecamatan
    KecamatanResponseDto updateKecamatan(String id, UpdateKecamatanRequestDto updateKecamatanRequestDto) throws KecamatanNotFoundException, KotaNotFoundException;

    // delete kecamatan
    void deleteKecamatan(String id);

    // get kecamatan by name
    KecamatanResponseDto getKecamatanByName(String name) throws KecamatanNotFoundException;

    // get kecamatan by name contains
    List<KecamatanResponseDto> getKecamatanByNameContains(String name);

    // get kecamatan by code
    KecamatanResponseDto getKecamatanByCode(String code) throws KecamatanNotFoundException;

    // get kecamatan by kota id, return is List of Kecamatan
    List<KecamatanResponseDto> getKecamatanByKotaId(String kotaId);
}
