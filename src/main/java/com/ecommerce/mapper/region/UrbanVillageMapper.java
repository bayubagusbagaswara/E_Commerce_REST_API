package com.ecommerce.mapper.region;

import com.ecommerce.dto.region.urbanVillage.UrbanVillageDTO;
import com.ecommerce.entity.region.UrbanVillage;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class UrbanVillageMapper {

    public UrbanVillageDTO fromUrbanVillage(UrbanVillage urbanVillage) {
        UrbanVillageDTO dto = new UrbanVillageDTO();
        dto.setId(urbanVillage.getId());
        dto.setCode(urbanVillage.getCode());
        dto.setName(urbanVillage.getName());
        dto.setCreatedAt(urbanVillage.getCreatedAt());
        dto.setUpdatedAt(urbanVillage.getUpdatedAt());
        dto.setSubDistrict(urbanVillage.getSubDistrict());
        return dto;
    }

    public List<UrbanVillageDTO> fromUrbanVillageList(List<UrbanVillage> urbanVillageList) {
        return urbanVillageList.stream()
                .map(this::fromUrbanVillage)
                .collect(Collectors.toList());
    }
}
