package com.restful.dto.kecamatan;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CreateKecamatanRequestDto {

    @NotBlank(message = "Kecamatan name must not be blank")
    private String name;

    @NotBlank(message = "Kecamatan code must not be blank")
    private String code;

    @NotBlank(message = "Kota ID must not be blank")
    private String kotaId;
}
