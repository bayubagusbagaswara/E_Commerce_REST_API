package com.ecommerce.mapper.region;

import com.ecommerce.dto.region.province.ProvinceDTO;
import com.ecommerce.entity.region.Province;
import org.springframework.stereotype.Component;

@Component
public class ProvinceMapper {

    public ProvinceDTO fromProvince(Province province) {
        ProvinceDTO provinceDTO = new ProvinceDTO();
        provinceDTO.setId(province.getId());
        provinceDTO.setCode(province.getCode());
        provinceDTO.setName(province.getName());
        provinceDTO.setCreatedAt(province.getCreatedAt());
        return provinceDTO;
    }
}
