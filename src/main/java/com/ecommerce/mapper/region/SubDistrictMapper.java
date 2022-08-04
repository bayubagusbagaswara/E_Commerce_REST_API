package com.ecommerce.mapper.region;

import com.ecommerce.dto.region.subDistrict.SubDistrictDTO;
import com.ecommerce.entity.region.SubDistrict;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class SubDistrictMapper {

    public SubDistrictDTO fromSubDistrict(SubDistrict subDistrict) {
        SubDistrictDTO dto = new SubDistrictDTO();
        dto.setId(subDistrict.getId());
        dto.setCode(subDistrict.getCode());
        dto.setName(subDistrict.getName());
        dto.setCreatedAt(subDistrict.getCreatedAt());
        dto.setDistrict(subDistrict.getDistrict());
        return dto;
    }

    public List<SubDistrictDTO> fromSubDistrictList(List<SubDistrict> subDistrictList) {
        return subDistrictList.stream()
                .map(this::fromSubDistrict)
                .collect(Collectors.toList());
    }

}
