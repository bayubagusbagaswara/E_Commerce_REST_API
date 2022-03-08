package com.restful.service;

import com.restful.dto.kelurahan.*;
import com.restful.exception.KecamatanNotFoundException;
import com.restful.exception.KelurahanNotFoundException;

import java.util.List;

public interface KelurahanService {

    // create kelurahan
    KelurahanResponseDto createKelurahan(CreateKelurahanRequestDto createKelurahanRequestDto) throws KecamatanNotFoundException;

    // get kelurahan by id
    KelurahanResponseDto getKelurahanById(String id) throws KelurahanNotFoundException;

    // get all kelurahan
    ListKelurahanResponseDto getAllKelurahan(ListKelurahanRequestDto listKelurahanRequestDto);

    // update kelurahan
    KelurahanResponseDto updateKelurahan(String id, UpdateKelurahanRequestDto updateKelurahanRequestDto) throws KelurahanNotFoundException, KecamatanNotFoundException;

    // delete kelurahan
    void deleteKelurahan(String id);

    // get kelurahan by name
    KelurahanResponseDto getKelurahanByName(String name) throws KelurahanNotFoundException;

    // get kelurahan by name contains
    List<KelurahanResponseDto> getKelurahanByNameContains(String name);

    // get kelurahan by code
    KelurahanResponseDto getKelurahanByCode(String code) throws KelurahanNotFoundException;

    // get kelurahan by kecamatan id, return is List of Kelurahan
    List<KelurahanResponseDto> getKelurahanByKecamatanId(String kecamatanId);
}
