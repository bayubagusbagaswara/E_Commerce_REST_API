package com.restful.service;

import com.restful.dto.kelurahan.*;
import com.restful.exception.KecamatanNotFoundException;
import com.restful.exception.KelurahanNotFoundException;

import java.util.List;

public interface KelurahanService {

    KelurahanResponseDto createKelurahan(CreateKelurahanRequestDto createKelurahanRequestDto) throws KecamatanNotFoundException;

    KelurahanResponseDto getKelurahanById(String id) throws KelurahanNotFoundException;

    ListKelurahanResponseDto getAllKelurahan(ListKelurahanRequestDto listKelurahanRequestDto);

    KelurahanResponseDto updateKelurahan(String id, UpdateKelurahanRequestDto updateKelurahanRequestDto) throws KelurahanNotFoundException, KecamatanNotFoundException;

    void deleteKelurahan(String id);

    KelurahanResponseDto getKelurahanByName(String name) throws KelurahanNotFoundException;

    List<KelurahanResponseDto> getKelurahanByNameContains(String name);

    KelurahanResponseDto getKelurahanByCode(String code) throws KelurahanNotFoundException;

    List<KelurahanResponseDto> getKelurahanByKecamatanId(String kecamatanId);
}
