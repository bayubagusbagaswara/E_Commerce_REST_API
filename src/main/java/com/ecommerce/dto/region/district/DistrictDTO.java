package com.ecommerce.dto.region.district;

import com.ecommerce.dto.region.province.ProvinceDTO;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.Instant;

@Data
@NoArgsConstructor
public class DistrictDTO {

    private String id;
    private String code;
    private String name;
    private Instant createdAt;
    private ProvinceDTO provinceDTO;
}
