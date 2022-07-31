package com.ecommerce.dto.region.urbanVillage;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class CreateKelurahanRequestDto {

    @NotBlank(message = "Kelurahan code must not be blank")
    private String code;

    @NotBlank(message = "Kelurahan name must not be blank")
    private String name;

    @NotBlank(message = "Kecamatan ID must not be blank")
    private String kecamatanId;
}
