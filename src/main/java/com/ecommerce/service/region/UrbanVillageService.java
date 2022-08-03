package com.ecommerce.service.region;

import com.ecommerce.dto.kelurahan.*;
import com.ecommerce.dto.region.urbanVillage.*;
import com.ecommerce.exception.KecamatanNotFoundException;
import com.ecommerce.exception.KelurahanNotFoundException;

import java.util.List;

public interface UrbanVillageService {

    UrbanVillageDTO createKelurahan(CreateUrbanVillageRequestDTO createKelurahanRequest) throws KecamatanNotFoundException;

    UrbanVillageDTO getKelurahanById(String id) throws KelurahanNotFoundException;

    ListKelurahanResponseDto getAllKelurahan(ListUrbanVillageRequestDTO listKelurahanRequest);

    UrbanVillageDTO updateKelurahan(String id, UpdateKelurahanRequestDto updateKelurahanRequest) throws KelurahanNotFoundException, KecamatanNotFoundException;

    void deleteKelurahan(String id) throws KelurahanNotFoundException;

    UrbanVillageDTO getKelurahanByName(String name) throws KelurahanNotFoundException;

    List<UrbanVillageDTO> getKelurahanByNameContains(String name);

    UrbanVillageDTO getKelurahanByCode(String code) throws KelurahanNotFoundException;

    List<UrbanVillageDTO> getKelurahanByKecamatanId(String kecamatanId);
}
