package com.ecommerce.dto.region.district;

import com.ecommerce.entity.region.Province;
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
    private Province province;
}
