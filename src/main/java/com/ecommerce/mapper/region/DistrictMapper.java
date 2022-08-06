package com.ecommerce.mapper.region;

import com.ecommerce.dto.region.district.DistrictDTO;
import com.ecommerce.entity.region.District;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class DistrictMapper {

    public DistrictDTO fromDistrict(District district) {
        DistrictDTO districtDTO = new DistrictDTO();
        districtDTO.setId(district.getId());
        districtDTO.setCode(district.getCode());
        districtDTO.setName(district.getName());
        districtDTO.setCreatedAt(district.getCreatedAt());
        districtDTO.setUpdatedAt(district.getUpdatedAt());
        districtDTO.setProvince(district.getProvince());
        return districtDTO;
    }

    public List<DistrictDTO> fromDistrictList(List<District> districtList) {
        return districtList.stream()
                .map(this::fromDistrict)
                .collect(Collectors.toList());
    }
}
