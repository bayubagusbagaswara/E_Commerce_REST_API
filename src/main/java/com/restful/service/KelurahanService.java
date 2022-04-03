package com.restful.service;

import com.restful.dto.kelurahan.*;
import com.restful.exception.KecamatanNotFoundException;
import com.restful.exception.KelurahanNotFoundException;

import java.util.List;

public interface KelurahanService {

    KelurahanResponseDto createKelurahan(CreateKelurahanRequestDto createKelurahanRequest) throws KecamatanNotFoundException;

    KelurahanResponseDto getKelurahanById(String id) throws KelurahanNotFoundException;

    ListKelurahanResponseDto getAllKelurahan(ListKelurahanRequestDto listKelurahanRequest);

    KelurahanResponseDto updateKelurahan(String id, UpdateKelurahanRequestDto updateKelurahanRequest) throws KelurahanNotFoundException, KecamatanNotFoundException;

    void deleteKelurahan(String id) throws KelurahanNotFoundException;

    KelurahanResponseDto getKelurahanByName(String name) throws KelurahanNotFoundException;

    List<KelurahanResponseDto> getKelurahanByNameContains(String name);

    KelurahanResponseDto getKelurahanByCode(String code) throws KelurahanNotFoundException;

    List<KelurahanResponseDto> getKelurahanByKecamatanId(String kecamatanId);
}
