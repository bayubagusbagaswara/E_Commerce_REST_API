package com.ecommerce.dto.region.urbanVillage;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class CreateUrbanVillageRequestDTO {

    @NotBlank(message = "Urban village code must not be blank")
    private String code;

    @NotBlank(message = "Urban village name must not be blank")
    private String name;

    @NotBlank(message = "Sub District ID must not be blank")
    private String subDistrictId;
}
