package com.ecommerce.dto.region.district;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class UpdateDistrictRequestDTO {

    @NotBlank(message = "District code must not be blank")
    private String code;

    @NotBlank(message = "District name must not be blank")
    private String name;

    @NotBlank(message = "Province ID must not be blank")
    private String provinceId;
}

