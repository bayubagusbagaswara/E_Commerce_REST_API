package com.ecommerce.dto.region.urbanVillage;

import com.ecommerce.dto.region.subDistrict.SubDistrictDTO;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class UrbanVillageDTO {

    private String id;
    private String code;
    private String name;
    private SubDistrictDTO subDistrictDTO;
}
