package com.ecommerce.dto.region.subDistrict;

import com.ecommerce.entity.region.District;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.Instant;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SubDistrictDTO {

    private String id;
    private String code;
    private String name;
    private Instant createdAt;
    private District district;
}
