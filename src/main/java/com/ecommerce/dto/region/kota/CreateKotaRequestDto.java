package com.ecommerce.dto.region.kota;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class CreateKotaRequestDto {

    @NotBlank(message = "Kota code must not be blank")
    private String code;

    @NotBlank(message = "Kota name must not be blank")
    private String name;

    @NotBlank(message = "Provinsi ID must not be blank")
    private String provinsiId;
}
