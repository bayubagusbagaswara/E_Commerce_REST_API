package com.ecommerce.dto.region.subDistrict;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class CreateKecamatanRequestDto {

    @NotBlank(message = "Kecamatan code must not be blank")
    private String code;

    @NotBlank(message = "Kecamatan name must not be blank")
    private String name;

    @NotBlank(message = "Kota ID must not be blank")
    private String kotaId;
}
