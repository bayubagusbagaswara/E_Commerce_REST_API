package com.ecommerce.dto.region.subDistrict;

import com.ecommerce.dto.region.district.DistrictDTO;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class SubDistrictDTO {

    private String id;
    private String code;
    private String name;
    private DistrictDTO districtDTO;
}
