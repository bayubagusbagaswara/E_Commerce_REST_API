package com.ecommerce.dto.region.province;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.Instant;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProvinceDTO {

    private String id;
    private String code;
    private String name;
    private Instant createdAt;
    private Instant updatedAt;
}
