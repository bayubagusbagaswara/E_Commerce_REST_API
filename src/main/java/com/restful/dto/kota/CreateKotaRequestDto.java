package com.restful.dto.kota;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CreateKotaRequestDto {

    @NotBlank(message = "Kota name must not be blank")
    private String name;

    @NotBlank(message = "Kota code must not be blank")
    private String code;

    @NotBlank(message = "Provinsi ID must not be blank")
    private String provinsiId;
}
