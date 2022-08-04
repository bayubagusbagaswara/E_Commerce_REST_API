package com.ecommerce.service.region;

import com.ecommerce.dto.kecamatan.*;
import com.ecommerce.dto.region.subDistrict.*;
import com.ecommerce.exception.SubDistrictNotFoundException;
import com.ecommerce.exception.DistrictNotFoundException;

import java.util.List;

public interface SubDistrictService {

    SubDistrictDTO createKecamatan(CreateSubDistrictRequestDTO createKecamatanRequest) throws DistrictNotFoundException;

    SubDistrictDTO getKecamatanById(String id) throws SubDistrictNotFoundException;

    ListSubDistrictResponseDTO getAllKecamatan(ListSubDistrictRequestDTO listKecamatanRequest);

    SubDistrictDTO updateKecamatan(String id, UpdateSubDistrictRequestDTO updateKecamatanRequest) throws SubDistrictNotFoundException, DistrictNotFoundException;

    void deleteKecamatan(String id) throws SubDistrictNotFoundException;

    SubDistrictDTO getKecamatanByName(String name) throws SubDistrictNotFoundException;

    List<SubDistrictDTO> getKecamatanByNameContains(String name);

    SubDistrictDTO getKecamatanByCode(String code) throws SubDistrictNotFoundException;

    List<SubDistrictDTO> getKecamatanByKotaId(String kotaId);
}
