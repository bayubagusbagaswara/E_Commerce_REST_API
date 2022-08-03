package com.ecommerce.dto.region.district;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class UpdateDistrictRequestDTO {

    @NotBlank(message = "Kota code must not be blank")
    private String code;

    @NotBlank(message = "Kota name must not be blank")
    private String name;

    @NotBlank(message = "Provinsi ID must not be blank")
    private String provinsiId;
}

