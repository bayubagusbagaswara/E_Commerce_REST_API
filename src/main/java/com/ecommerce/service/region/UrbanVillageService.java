package com.ecommerce.service.region;

import com.ecommerce.dto.region.urbanVillage.*;

import java.util.List;

public interface UrbanVillageService {

    UrbanVillageDTO createUrbanVillage(CreateUrbanVillageRequestDTO createUrbanVillageRequestDTO);

    UrbanVillageDTO getUrbanVillageById(String urbanVillageId);

    ListUrbanVillageResponseDTO getAllUrbanVillages(ListUrbanVillageRequestDTO listUrbanVillageRequestDTO);

    UrbanVillageDTO updateUrbanVillage(String urbanVillageId, UpdateUrbanVillageRequestDTO updateUrbanVillageRequestDTO);

    void deleteUrbanVillage(String urbanVillageId);

    UrbanVillageDTO getUrbanVillageByName(String name);

    List<UrbanVillageDTO> getUrbanVillageByNameContains(String name);

    UrbanVillageDTO getUrbanVillageByCode(String code);

    List<UrbanVillageDTO> getAllUrbanVillagesBySubDistrictId(String subDistrictId);
}
