package com.ecommerce.mapper.region;

import com.ecommerce.dto.region.district.DistrictDTO;
import com.ecommerce.entity.region.District;
import org.springframework.stereotype.Component;

@Component
public class DistrictMapper {

    public DistrictDTO fromEntity(District district) {
        DistrictDTO districtDTO = new DistrictDTO();
        districtDTO.setId(district.getId());
        districtDTO.setCode(district.getCode());
        districtDTO.setName(district.getName());
        districtDTO.setCreatedAt(district.getCreatedAt());
        districtDTO.setProvince(district.getProvince());
        return districtDTO;
    }
}
