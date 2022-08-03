package com.ecommerce.dto.region.subDistrict;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class CreateSubDistrictRequestDTO {

    @NotBlank(message = "Sub District code must not be blank")
    private String code;

    @NotBlank(message = "Sub District name must not be blank")
    private String name;

    @NotBlank(message = "District ID must not be blank")
    private String districtId;
}
