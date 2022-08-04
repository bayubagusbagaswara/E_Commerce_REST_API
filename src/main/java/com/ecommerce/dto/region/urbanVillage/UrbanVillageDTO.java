package com.ecommerce.dto.region.urbanVillage;

import com.ecommerce.entity.region.SubDistrict;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.Instant;

@Data
@NoArgsConstructor
public class UrbanVillageDTO {

    private String id;
    private String code;
    private String name;
    private Instant createdAt;
    private SubDistrict subDistrict;
}
