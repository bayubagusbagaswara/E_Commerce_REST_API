package com.ecommerce.service.region;

import com.ecommerce.dto.kelurahan.*;
import com.ecommerce.dto.region.urbanVillage.*;
import com.ecommerce.exception.SubDistrictNotFoundException;
import com.ecommerce.exception.UrbanVillageNotFoundException;

import java.util.List;

public interface UrbanVillageService {

    UrbanVillageDTO createKelurahan(CreateUrbanVillageRequestDTO createKelurahanRequest) throws SubDistrictNotFoundException;

    UrbanVillageDTO getKelurahanById(String id) throws UrbanVillageNotFoundException;

    ListUrbanVillageResponseDTO getAllKelurahan(ListUrbanVillageRequestDTO listKelurahanRequest);

    UrbanVillageDTO updateKelurahan(String id, UpdateUrbanVillageRequestDTO updateKelurahanRequest) throws UrbanVillageNotFoundException, SubDistrictNotFoundException;

    void deleteKelurahan(String id) throws UrbanVillageNotFoundException;

    UrbanVillageDTO getKelurahanByName(String name) throws UrbanVillageNotFoundException;

    List<UrbanVillageDTO> getKelurahanByNameContains(String name);

    UrbanVillageDTO getKelurahanByCode(String code) throws UrbanVillageNotFoundException;

    List<UrbanVillageDTO> getKelurahanByKecamatanId(String kecamatanId);
}
