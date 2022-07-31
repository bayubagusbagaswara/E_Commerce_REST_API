package com.restful.service;

import com.restful.dto.kecamatan.*;
import com.restful.dto.region.kecamatan.*;
import com.restful.exception.KecamatanNotFoundException;
import com.restful.exception.KotaNotFoundException;

import java.util.List;

public interface KecamatanService {

    KecamatanResponseDto createKecamatan(CreateKecamatanRequestDto createKecamatanRequest) throws KotaNotFoundException;

    KecamatanResponseDto getKecamatanById(String id) throws KecamatanNotFoundException;

    ListKecamatanResponseDto getAllKecamatan(ListKecamatanRequestDto listKecamatanRequest);

    KecamatanResponseDto updateKecamatan(String id, UpdateKecamatanRequestDto updateKecamatanRequest) throws KecamatanNotFoundException, KotaNotFoundException;

    void deleteKecamatan(String id) throws KecamatanNotFoundException;

    KecamatanResponseDto getKecamatanByName(String name) throws KecamatanNotFoundException;

    List<KecamatanResponseDto> getKecamatanByNameContains(String name);

    KecamatanResponseDto getKecamatanByCode(String code) throws KecamatanNotFoundException;

    List<KecamatanResponseDto> getKecamatanByKotaId(String kotaId);
}
