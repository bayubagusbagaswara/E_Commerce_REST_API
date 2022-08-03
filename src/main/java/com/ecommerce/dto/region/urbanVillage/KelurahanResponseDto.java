package com.ecommerce.dto.region.urbanVillage;

import com.ecommerce.dto.region.subDistrict.SubDistrictDTO;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
public class KelurahanResponseDto {

    private String id;
    private String code;
    private String name;
    private LocalDateTime createdDate;
    private LocalDateTime updatedDate;
    private SubDistrictDTO kecamatan;
}
