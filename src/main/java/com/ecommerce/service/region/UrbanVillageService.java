package com.ecommerce.service.region;

import com.ecommerce.dto.kelurahan.*;
import com.ecommerce.dto.region.urbanVillage.*;
import com.ecommerce.exception.SubDistrictNotFoundException;
import com.ecommerce.exception.KelurahanNotFoundException;

import java.util.List;

public interface UrbanVillageService {

    UrbanVillageDTO createKelurahan(CreateUrbanVillageRequestDTO createKelurahanRequest) throws SubDistrictNotFoundException;

    UrbanVillageDTO getKelurahanById(String id) throws KelurahanNotFoundException;

    ListUrbanVillageResponseDTO getAllKelurahan(ListUrbanVillageRequestDTO listKelurahanRequest);

    UrbanVillageDTO updateKelurahan(String id, UpdateUrbanVillageRequestDTO updateKelurahanRequest) throws KelurahanNotFoundException, SubDistrictNotFoundException;

    void deleteKelurahan(String id) throws KelurahanNotFoundException;

    UrbanVillageDTO getKelurahanByName(String name) throws KelurahanNotFoundException;

    List<UrbanVillageDTO> getKelurahanByNameContains(String name);

    UrbanVillageDTO getKelurahanByCode(String code) throws KelurahanNotFoundException;

    List<UrbanVillageDTO> getKelurahanByKecamatanId(String kecamatanId);
}
