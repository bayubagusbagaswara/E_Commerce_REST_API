package com.ecommerce.service.region;

import com.ecommerce.dto.kelurahan.*;
import com.ecommerce.dto.region.urbanVillage.*;
import com.ecommerce.exception.KecamatanNotFoundException;
import com.ecommerce.exception.KelurahanNotFoundException;

import java.util.List;

public interface UrbanVillageService {

    KelurahanResponseDto createKelurahan(CreateUrbanVillageRequestDTO createKelurahanRequest) throws KecamatanNotFoundException;

    KelurahanResponseDto getKelurahanById(String id) throws KelurahanNotFoundException;

    ListKelurahanResponseDto getAllKelurahan(ListKelurahanRequestDto listKelurahanRequest);

    KelurahanResponseDto updateKelurahan(String id, UpdateKelurahanRequestDto updateKelurahanRequest) throws KelurahanNotFoundException, KecamatanNotFoundException;

    void deleteKelurahan(String id) throws KelurahanNotFoundException;

    KelurahanResponseDto getKelurahanByName(String name) throws KelurahanNotFoundException;

    List<KelurahanResponseDto> getKelurahanByNameContains(String name);

    KelurahanResponseDto getKelurahanByCode(String code) throws KelurahanNotFoundException;

    List<KelurahanResponseDto> getKelurahanByKecamatanId(String kecamatanId);
}
