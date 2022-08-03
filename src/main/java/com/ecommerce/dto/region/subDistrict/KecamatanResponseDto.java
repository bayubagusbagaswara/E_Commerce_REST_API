package com.ecommerce.dto.region.subDistrict;

import com.ecommerce.dto.region.district.DistrictDTO;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
public class KecamatanResponseDto {

    private String id;
    private String code;
    private String name;
    private LocalDateTime createdDate;
    private LocalDateTime updatedDate;
    private DistrictDTO kota;
}
